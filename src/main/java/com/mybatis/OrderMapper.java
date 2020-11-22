package com.mybatis;

import com.pojo.Order;

import java.util.List;

/**
 * @author leofee
 */
public interface OrderMapper {

    List<Order> query(Order condition);

    Order findByPrimaryKey(Integer orderId);

    void insertUseGeneratedKeys(Order order);

    void insertBySelectKey(Order order);
}
