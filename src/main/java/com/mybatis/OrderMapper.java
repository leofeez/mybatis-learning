package com.mybatis;

import com.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author leofee
 */
public interface OrderMapper {

    List<Order> query(Order condition);

    List<Order> queryByOrderType(@Param("orderType")String orderType);

    List<Order> queryByOrderTypeFlushCache(@Param("orderType")String orderType, @Param("userId")Integer userId);

    Order findByPrimaryKey(Integer orderId);

    Order findUserOrderByPrimaryKey(Integer orderId);

    Order findUserOrderByPrimaryKey2(Integer orderId);

    void insertUseGeneratedKeys(Order order);

    void insertBySelectKey(Order order);

    void insertBatch(List<Order> orders);

    void insertBatchGenerateKey(List<Order> orders);
}
