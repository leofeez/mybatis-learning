package com.mybatis.mapper;

import com.pojo.Order;

import java.util.List;

/**
 * @author leofee
 */
public interface OrderMapper {

    List<Order> query(Order condition);
}
