import com.mybatis.OrderMapper;
import com.mybatis.mapper.RoleMapper;
import com.mybatis.mapper.UserMapper;
import com.pojo.Order;
import com.pojo.Role;
import com.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyBatisMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(Resources.getResourceAsStream("mybatis.xml"));
    }

    @Test
    public void getOrder() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = mapper.findByPrimaryKey(1);
        System.out.println("order = " + order.toString());
    }

    @Test
    public void insertUseGeneratedKeys() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = new Order();
        order.setOrderCode("202011222300");
        order.setCreateTime(LocalDateTime.now());
        mapper.insertUseGeneratedKeys(order);
        sqlSession.commit();
        System.out.println("order = " + order.getOrderId());
    }

    @Test
    public void insertBySelectKey() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = new Order();
        order.setOrderCode("202011222301");
        order.setCreateTime(LocalDateTime.now());
        mapper.insertUseGeneratedKeys(order);
        sqlSession.commit();
        System.out.println("order = " + order.getOrderId());
    }

    @Test
    public void queryOrder() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orders = mapper.query(new Order());
        for (Order order : orders) {
            System.out.println(order.toString());
        }
        sqlSession.close();
    }

    @Test
    public void queryUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.query(new User());
        for (User user : users) {
            System.out.println(user.toString());
        }
        sqlSession.close();
    }

    /**
     * 如果{@code ResultMap} 对应的实体没有无参构造，则必须指定 {@code constructor}
     */
    @Test
    public void queryRole() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        Role role = mapper.selectByPrimaryKey(1);
        System.out.println("role = " + role);
        sqlSession.close();
    }

    /**
     * 如果在xml中不指定userGenerateKey=true，则不会返回主键ID
     */
    @Test
    public void insertOrderBatchWithoutGenerateKey() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 指定批量操作
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, true);
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = new Order();
        order.setOrderCode("2020111201");
        order.setCreateTime(LocalDateTime.now());
        Order order2 = new Order();
        order2.setOrderCode("2020111202");
        order2.setCreateTime(LocalDateTime.now());
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);
        mapper.insertBatch(orderList);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 在xml中指定useGenerateKey = true 则会返回主键ID
     */
    @Test
    public void insertOrderBatchWithGenerateKey() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 指定批量操作
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, true);
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = new Order();
        order.setOrderCode("2020111201");
        order.setCreateTime(LocalDateTime.now());
        Order order2 = new Order();
        order2.setOrderCode("2020111202");
        order2.setCreateTime(LocalDateTime.now());
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);
        mapper.insertBatchGenerateKey(orderList);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 在xml中指定useGenerateKey = true 则会返回主键ID,
     * ExecutorType.BATCH 时，只有在事务提交时才会返回主键ID
     */
    @Test
    public void insertOrderBatch() {
        // 指定批量操作
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, true);
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = new Order();
        order.setOrderCode("2020111301");
        order.setCreateTime(LocalDateTime.now());
        mapper.insertUseGeneratedKeys(order);
        Order order2 = new Order();
        order2.setOrderCode("2020111302");
        order2.setCreateTime(LocalDateTime.now());
        mapper.insertUseGeneratedKeys(order2);
        sqlSession.commit();
        sqlSession.close();
    }
}
