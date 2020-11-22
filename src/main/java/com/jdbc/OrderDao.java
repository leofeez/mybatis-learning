package com.jdbc;

import com.pojo.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao {

    public List<Order> queryAllOrder() {
        List<Order> orderList = new ArrayList<>();

        Connection connection = DatabaseConnectionFactory.getConnection(DatabaseType.MYSQL);

        PreparedStatement preparedStatement = null;
        try {
            // 预编译sql
            String sql = "select * from `learning`.order where order_code = ?;";
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数
            preparedStatement.setString(1, "1");
            ResultSet resultSet = preparedStatement.executeQuery();

            // 解析结果集
            while (resultSet.next()) {
                Order order = new Order();
                String orderCode = resultSet.getString("order_code");
                order.setOrderCode(orderCode);
                order.setOrderId(resultSet.getInt("order_id"));
                order.setCreateTime(LocalDateTime.ofInstant(new Date(resultSet.getDate("create_time").getTime()).toInstant(), ZoneId.systemDefault()));
                orderList.add(order);
            }

            // 关闭连接
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
