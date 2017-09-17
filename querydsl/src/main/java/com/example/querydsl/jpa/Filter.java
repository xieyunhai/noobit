package com.example.querydsl.jpa;

import lombok.Data;

import java.io.Serializable;

@Data
public class Filter implements Serializable {

    private static final long serialVersionUID = 4518844404759613602L;

    /**
     * 运算符
     */
    public enum Operator {

        /** 等于 */
        eq(" = "),

        /** 不等于 */
        ne(" != "),

        /** 大于 */
        gt(" > "),

        /** 小于 */
        lt(" < "),

        /** 大于等于 */
        ge(" >= "),

        /** 小于等于 */
        le(" <= "),

        /** 类似 */
        like(" like "),

        /** 包含 */
        in(" in "),

        /** 为Null */
        isNull(" is NULL "),

        /** 不为Null */
        isNotNull(" is not NULL ");
        Operator(String operator) {
            this.operator = operator;
        }

        private String operator;

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }
    }

    /** 默认是否忽略大小写 */
    private static final boolean DEFAULT_IGNORE_CASE = false;

    /** 属性 */
    private String property;

    /** 运算符 */
    private Filter.Operator operator;

    /** 值 */
    private Object value;

    /** 是否忽略大小写 */
    private Boolean ignoreCase = DEFAULT_IGNORE_CASE;

    /**
     * 构造方法
     */
    public Filter() { }

    /**
     * 构造方法
     *
     * @param property 属性
     * @param operator 运算符
     * @param value 值
     */
    public Filter(String property, Filter.Operator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    /**
     * 构造方法
     *
     * @param property 属性
     * @param operator 运算符
     * @param value 值
     * @param ignoreCase 忽略大小写
     */
    public Filter(String property, Filter.Operator operator, Object value, boolean ignoreCase) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = ignoreCase;
    }

    /**
     * 返回等于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 等于筛选
     */
    public static Filter eq(String property, Object value) {
        return new Filter(property, Filter.Operator.eq, value);
    }

    /**
     * 返回等于筛选
     *
     * @param property 属性
     * @param value 值
     * @param ignoreCase 忽略大小写
     * @return 等于筛选
     */
    public static Filter eq(String property, Object value, boolean ignoreCase) {
        return new Filter(property, Filter.Operator.eq, value, ignoreCase);
    }

    /**
     * 返回不等于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 不等于筛选
     */
    public static Filter ne(String property, Object value) {
        return new Filter(property, Filter.Operator.ne, value);
    }

    /**
     * 返回不等于筛选
     *
     * @param property 属性
     * @param value 值
     * @param ignoreCase 忽略大小写
     * @return 不等于筛选
     */
    public static Filter ne(String property, Object value, boolean ignoreCase) {
        return new Filter(property, Filter.Operator.ne, value, ignoreCase);
    }

    /**
     * 返回大于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 大于筛选
     */
    public static Filter gt(String property, Object value) {
        return new Filter(property, Filter.Operator.gt, value);
    }

    /**
     * 返回小于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 小于筛选
     */
    public static Filter lt(String property, Object value) {
        return new Filter(property, Filter.Operator.lt, value);
    }

    /**
     * 返回大于等于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 大于等于筛选
     */
    public static Filter ge(String property, Object value) {
        return new Filter(property, Filter.Operator.ge, value);
    }

    /**
     * 返回小于等于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 小于等于筛选
     */
    public static Filter le(String property, Object value) {
        return new Filter(property, Filter.Operator.le, value);
    }

    /**
     * 返回相似筛选
     *
     * @param property 属性
     * @param value 值
     * @return 相似筛选
     */
    public static Filter like(String property, Object value) {
        return new Filter(property, Filter.Operator.like, value);
    }

    /**
     * 返回包含筛选
     *
     * @param property 属性
     * @param value 值
     * @return 包含筛选
     */
    public static Filter in(String property, Object value) {
        return new Filter(property, Filter.Operator.in, value);
    }

    /**
     * 返回为 Null 筛选
     *
     * @param property 属性
     * @return 为Null筛选
     */
    public static Filter isNull(String property) {
        return new Filter(property, Filter.Operator.isNull, null);
    }

    /**
     * 返回不为Null筛选
     *
     * @param property 属性
     * @return 不为Null筛选
     */
    public static Filter isNotNull(String property) {
        return new Filter(property, Filter.Operator.isNotNull, null);
    }

    /**
     * 返回忽略大小写筛选
     *
     * @return 忽略大小写筛选
     */
    public Filter ignoreCase() {
        this.ignoreCase = true;
        return this;
    }

}
