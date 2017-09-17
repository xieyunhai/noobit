package com.example.querydsl.jpa;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
    private static final long serialVersionUID = -3078342809727773232L;

    /**
     * 方向
     */
    public enum Direction {
        asc,
        desc
    }

    /** 默认方向 */
    private static final Order.Direction DEFAULT_DIRECTION = Order.Direction.desc;

    /** 属性 */
    private String property;
    /** 方向 */
    private Order.Direction direction = DEFAULT_DIRECTION;

    /**
     * 构造方法
     */
    public Order() { }

    /**
     * 构造方法
     *
     * @param property 属性
     * @param direction 方向
     */
    public Order(String property, Order.Direction direction) {
        this.property = property;
        this.direction = direction;
    }

    /**
     * 返回递增排序
     *
     * @param property 属性
     * @return 递增排序
     */
    public static Order asc(String property) {
        return new Order(property, Order.Direction.asc);
    }

    /**
     * 返回递减排序
     *
     * @param property 属性
     * @return 递减排序
     */
    public static Order desc(String property) {
        return new Order(property, Order.Direction.desc);
    }
}
