package com.example.querydsl.jpa;

import lombok.Data;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class QueryParams<T> implements Specification<T> {
    /**
     * 属性分隔符
     */
    private static final String PROPERTY_SEPARATOR = ".";
    /**
     * and条件
     */
    private List<Filter> andFilters = new ArrayList<>();
    /**
     * or条件
     */
    private List<Filter> orFilters = new ArrayList<>();
    /**
     * 排序属性
     */
    private List<Order> orders = new ArrayList<>();

    /**
     * 添加一个 and 条件
     *
     * @param filter 该条件
     * @return 链式调用
     */
    public QueryParams and(Filter filter) {
        this.andFilters.add(filter);
        return this;
    }

    /**
     * 添加多个 and 条件
     *
     * @param filter 该条件
     * @return 链式调用
     */
    public QueryParams and(Filter... filter) {
        this.andFilters.addAll(Arrays.asList(filter));
        return this;
    }

    /**
     * 添加一个 or 条件
     *
     * @param filter 该条件
     * @return 链式调用
     */
    public QueryParams or(Filter filter) {
        this.orFilters.add(filter);
        return this;
    }

    /**
     * 添加多个 or 条件
     *
     * @param filter 该条件
     * @return 链式调用
     */
    public QueryParams or(Filter... filter) {
        this.orFilters.addAll(Arrays.asList(filter));
        return this;
    }

    /**
     * 升序字段
     *
     * @param property 该字段对应变量名
     * @return 链式调用
     */
    public QueryParams orderASC(String property) {
        this.orders.add(Order.asc(property));
        return this;
    }

    /**
     * 降序字段
     *
     * @param property 该字段对应变量名
     * @return 链式调用
     */
    public QueryParams orderDESC(String property) {
        this.orders.add(Order.desc(property));
        return this;
    }

    /**
     * 清除所有条件
     *
     * @return 该实例
     */
    public QueryParams clearAll() {
        if (!this.andFilters.isEmpty()) this.andFilters.clear();
        if (!this.orFilters.isEmpty()) this.orFilters.clear();
        if (!this.orders.isEmpty()) this.orders.clear();
        return this;
    }

    /**
     * 清除 and 条件
     *
     * @return 该实例
     */
    public QueryParams clearAnd() {
        if (!this.andFilters.isEmpty()) this.andFilters.clear();
        return this;
    }

    /**
     * 清除 or 条件
     *
     * @return 该实例
     */
    public QueryParams clearOr() {
        if (!this.orFilters.isEmpty()) this.andFilters.clear();
        return this;
    }

    /**
     * 清除 order 条件
     *
     * @return 该实例
     */
    public QueryParams clearOrder() {
        if (!this.orders.isEmpty()) this.orders.clear();
        return this;
    }

    /**
     * 生成条件的
     *
     * @param root  该对象的封装
     * @param query 查询构建器
     * @param cb    构建器
     * @return 条件集合
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate restrictions = cb.and(toAndPredicate(root, cb));
        // 如果无 or 条件,toOrPredicate会 自带一个 or 1=1, 前面用 and 的话则变为 and 1=1 造成错误, 所以这里判断下
        // 这个貌似和版本有关, 公司线上版本出现这个问题, 本地测试没问题
        if (!orders.isEmpty()) {
            restrictions = cb.and(restrictions, toOrPredicate(root, cb));
        }
        query.orderBy(toOrders(root, cb));
        return restrictions;
    }

    /**
     * 转换为 Predicate
     *
     * @param root Root
     * @return Predicate
     */
    @SuppressWarnings("unchecked")
    private Predicate toAndPredicate(Root<T> root, CriteriaBuilder cb) {
        Predicate restrictions = cb.conjunction();
        if (root == null || CollectionUtils.isEmpty(andFilters)) {
            return restrictions;
        }
        for (Filter filter : andFilters) {
            if (filter == null) {
                continue;
            }
            String property = filter.getProperty();
            Filter.Operator operator = filter.getOperator();
            Object value = filter.getValue();
            Boolean ignoreCase = filter.getIgnoreCase();
            Path<?> path = getPath(root, property);
            if (path == null) {
                continue;
            }
            switch (operator) {
                case eq:
                    if (value != null) {
                        if (BooleanUtils.isTrue(ignoreCase) && String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                            restrictions = cb.and(restrictions, cb.equal(cb.lower((Path<String>) path), ((String) value).toLowerCase()));
                        } else {
                            restrictions = cb.and(restrictions, cb.equal(path, value));
                        }
                    } else {
                        restrictions = cb.and(restrictions, path.isNull());
                    }
                    break;
                case ne:
                    if (value != null) {
                        if (BooleanUtils.isTrue(ignoreCase) && String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                            restrictions = cb.and(restrictions, cb.notEqual(cb.lower((Path<String>) path), ((String) value).toLowerCase()));
                        } else {
                            restrictions = cb.and(restrictions, cb.notEqual(path, value));
                        }
                    } else {
                        restrictions = cb.and(restrictions, path.isNotNull());
                    }
                    break;
                case gt:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = cb.and(restrictions, cb.gt((Path<Number>) path, (Number) value));
                    }
                    break;
                case lt:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = cb.and(restrictions, cb.lt((Path<Number>) path, (Number) value));
                    }
                    break;
                case ge:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = cb.and(restrictions, cb.ge((Path<Number>) path, (Number) value));
                    }
                    break;
                case le:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = cb.and(restrictions, cb.le((Path<Number>) path, (Number) value));
                    }
                    break;
                case like:
                    if (String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                        if (BooleanUtils.isTrue(ignoreCase)) {
                            restrictions = cb.and(restrictions, cb.like(cb.lower((Path<String>) path), ((String) value).toLowerCase()));
                        } else {
                            restrictions = cb.and(restrictions, cb.like((Path<String>) path, (String) value));
                        }
                    }
                    break;
                case in:
                    restrictions = cb.and(restrictions, path.in(value));
                    break;
                case isNull:
                    restrictions = cb.and(restrictions, path.isNull());
                    break;
                case isNotNull:
                    restrictions = cb.and(restrictions, path.isNotNull());
                    break;
            }
        }
        return restrictions;
    }

    /**
     * 转换为 Predicate
     *
     * @param root Root
     * @return Predicate
     */
    @SuppressWarnings("unchecked")
    private Predicate toOrPredicate(Root<T> root, CriteriaBuilder cb) {
        Predicate restrictions = cb.disjunction();
        if (root == null || CollectionUtils.isEmpty(andFilters)) {
            return restrictions;
        }
        for (Filter filter : orFilters) {
            if (filter == null) {
                continue;
            }
            String property = filter.getProperty();
            Filter.Operator operator = filter.getOperator();
            Object value = filter.getValue();
            Boolean ignoreCase = filter.getIgnoreCase();
            Path<?> path = getPath(root, property);
            if (path == null) {
                continue;
            }
            switch (operator) {
                case eq:
                    if (value != null) {
                        if (BooleanUtils.isTrue(ignoreCase) && String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                            restrictions = cb.or(restrictions, cb.equal(cb.lower((Path<String>) path), ((String) value).toLowerCase()));
                        } else {
                            restrictions = cb.or(restrictions, cb.equal(path, value));
                        }
                    } else {
                        restrictions = cb.or(restrictions, path.isNull());
                    }
                    break;
                case ne:
                    if (value != null) {
                        if (BooleanUtils.isTrue(ignoreCase) && String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                            restrictions = cb.or(restrictions, cb.notEqual(cb.lower((Path<String>) path), ((String) value).toLowerCase()));
                        } else {
                            restrictions = cb.or(restrictions, cb.notEqual(path, value));
                        }
                    } else {
                        restrictions = cb.or(restrictions, path.isNotNull());
                    }
                    break;
                case gt:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = cb.or(restrictions, cb.gt((Path<Number>) path, (Number) value));
                    }
                    break;
                case lt:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = cb.or(restrictions, cb.lt((Path<Number>) path, (Number) value));
                    }
                    break;
                case ge:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = cb.or(restrictions, cb.ge((Path<Number>) path, (Number) value));
                    }
                    break;
                case le:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = cb.or(restrictions, cb.le((Path<Number>) path, (Number) value));
                    }
                    break;
                case like:
                    if (String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                        if (BooleanUtils.isTrue(ignoreCase)) {
                            restrictions = cb.or(restrictions, cb.like(cb.lower((Path<String>) path), ((String) value).toLowerCase()));
                        } else {
                            restrictions = cb.or(restrictions, cb.like((Path<String>) path, (String) value));
                        }
                    }
                    break;
                case in:
                    restrictions = cb.or(restrictions, path.in(value));
                    break;
                case isNull:
                    restrictions = cb.or(restrictions, path.isNull());
                    break;
                case isNotNull:
                    restrictions = cb.or(restrictions, path.isNotNull());
                    break;
            }
        }
        return restrictions;
    }


    /**
     * 转换为 Order
     *
     * @param root Root
     * @return Order
     */
    private List<javax.persistence.criteria.Order> toOrders(Root<T> root, CriteriaBuilder cb) {
        List<javax.persistence.criteria.Order> orderList = new ArrayList<>();
        if (root == null || CollectionUtils.isEmpty(orders)) {
            return orderList;
        }
        for (Order order : orders) {
            if (order == null) {
                continue;
            }
            String property = order.getProperty();
            Order.Direction direction = order.getDirection();
            Path<?> path = getPath(root, property);
            if (path == null || direction == null) {
                continue;
            }
            switch (direction) {
                case asc:
                    orderList.add(cb.asc(path));
                    break;
                case desc:
                    orderList.add(cb.desc(path));
                    break;
            }
        }
        return orderList;
    }

    /**
     * 获取Path
     *
     * @param path         Path
     * @param propertyPath 属性路径
     * @return Path
     */
    @SuppressWarnings("unchecked")
    private <X> Path<X> getPath(Path<?> path, String propertyPath) {
        if (path == null || StringUtils.isEmpty(propertyPath)) {
            return (Path<X>) path;
        }
        String property = StringUtils.substringBefore(propertyPath, PROPERTY_SEPARATOR);
        return getPath(path.get(property), StringUtils.substringAfter(propertyPath, PROPERTY_SEPARATOR));
    }
}
