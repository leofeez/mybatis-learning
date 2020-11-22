# mybatis-learning
mybatis 是一个 `ORM（Object Relation Mapping`框架

###1. 原生jdbc操作数据库
- 加载数据库驱动
```java
    Class.forName("com.mysql.cj.jdbc.Driver");
```

- 获取数据库连接
```java
    Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
```

- 预编译sql，查询，解析结果，关闭数据库连接
```java
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
```

###2. 快速开始

###3. 技术解析

- ResultMap
如果 ResultMap指定的映射对象没有声明无参构造，则需要在mapper.xml的ResultMap中指定对应的构造方法
```xml
    <resultMap id="BaseResultMap" type="com.pojo.Role">
        <!-- 需要和映射的实体对象构造方法参数一致-->
        <constructor>
            <arg column="role_name" javaType="string"/>
        </constructor>
        <id column="role_id" jdbcType="INTEGER" property="roleId"/>
        <result column="role_name" jdbcType="VARCHAR" property="roleName"/>
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime"/>
    </resultMap>
```
- 