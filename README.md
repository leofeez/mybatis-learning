# mybatis-learning
mybatis 是一个 `ORM（Object Relation Mapping）`框架

### 原生jdbc操作数据库

1. 加载数据库驱动
    
    ```java
        Class.forName("com.mysql.cj.jdbc.Driver");
    ```
    
2. 获取数据库连接

    ```java
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    ```
    
3.  预编译sql，查询，解析结果，关闭数据库连接

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

### 快速开始
mybatis.xml配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
    
    <configuration>
        <!-- 引入db配置-->
        <properties resource="db.properties"/>
    
        <settings>
            <!--该配置影响所有映射器中配置的缓存的全局开关-->
            <setting name="cacheEnabled" value="true"/>
            <!-- 延迟加载的全局开关，当开启时，所有的关联对象都会延迟加载。特定关联关系中可以通过设置fetchType属性来覆盖该项的开关-->
            <setting name="lazyLoadingEnabled" value="false"/>
            <!-- 当启用时，对任意延迟属性的调用会使带有延迟加载属性的对象完整加载，反之，每种属性将按需加载-->
            <setting name="aggressiveLazyLoading" value="true"/>
            <!-- 指定MyBatis 应如何自动映射到字段或属性，NONE 表示取消自动映射，PARTIAL 只会自动映射没有定义嵌套结果集映射的结果集，FULL
            会自动映射任意复杂的结果集（无论是否嵌套）-->
            <setting name="autoMappingBehavior" value="PARTIAL"/>
            <!-- 自动将数据库字段根据驼峰规则映射为实体属性-->
            <setting name="mapUnderscoreToCamelCase" value="true"/>
        </settings>
    
        <!-- 给 resultType 起别名 -->
        <typeAliases>
            <package name="com.pojo"/>
        </typeAliases>
    
        <!-- 环境 -->
        <environments default="development">
            <environment id="development">
                <transactionManager type="JDBC"/>
                <!-- 配置数据库连接信息 -->
                <dataSource type="POOLED">
                    <property name="driver" value="${driver}"/>
                    <property name="url" value="${url}"/>
                    <property name="username" value="${username}"/>
                    <property name="password" value="${password}"/>
                </dataSource>
            </environment>
        </environments>
    
        <mappers>
            <!-- 指定 mapper xml -->
            <mapper resource="mapper/OrderMapper.xml"/>
            <!-- 指定mapper接口-->
    <!--        <mapper class="com.mybatis.mapper.UserMapper"/>-->
            <!-- 可以指定包路径-->
            <package name="com.mybatis.mapper"/>
        </mappers>
    </configuration>
```
### 技术解析

#### ResultMap 标签
1. `<constructor/>` :如果 ResultMap指定的映射对象没有声明无参构造，则需要在mapper.xml的ResultMap中指定对应的构造方法
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
2. 
