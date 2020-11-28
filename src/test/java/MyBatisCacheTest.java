import com.mybatis.OrderCacheMapper;
import com.mybatis.OrderMapper;
import com.pojo.Order;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author leofee
 * @date 2020/11/28
 */
public class MyBatisCacheTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = sqlSessionFactoryBuilder.build(this.getClass().getResourceAsStream("mybatis.xml"));
    }

    /**
     * mybatis 一级缓存
     *
     * <li> 一级缓存默认是启动的，想要关闭一级缓存则需要在对应的select 标签上指定flushCache="true"
     * <li> 一级缓存只存在于sqlSession的生命周期内，只有在同一个sqlSession中才会进行两次相同的sql的查询才会命中缓存
     * <li> 任何的insert, update, delete 都会清空一级缓存
     */
    @Test
    public void queryOrderByOrderType () {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        // 同一个sqlSession 第一次查询
        List<Order> orderList = mapper.queryByOrderType("1");
        for (Order order : orderList) {
            System.out.println(order.toString());
        }

        // 同一个sqlSession 第二次查询会直接返回一级缓存中的数据
        List<Order> orderList2 = mapper.queryByOrderType("1");
        for (Order order : orderList2) {
            System.out.println(order.toString());
        }
    }

    @Test
    public void queryOrderByOrderTypeFlushCache () {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        // 同一个sqlSession 第一次查询
        List<Order> orderList = mapper.queryByOrderTypeFlushCache("1", 1);
        for (Order order : orderList) {
            System.out.println(order.toString());
        }

        // 同一个sqlSession 第二次查询由于设置了flushCache="true"，所以还是会查询数据库
        List<Order> orderList2 = mapper.queryByOrderTypeFlushCache("1", 1);
        for (Order order : orderList2) {
            System.out.println(order.toString());
        }
    }

    @Test
    public void queryOrderByOrderTypeThenInsert () {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        // 同一个sqlSession 第一次查询
        List<Order> orderList = mapper.queryByOrderType("1");
        for (Order order : orderList) {
            System.out.println(order.toString());
        }

        // 新增会导致缓存清空
        Order order = new Order();
        order.setOrderCode("2020111201");
        order.setCreateTime(LocalDateTime.now());
        order.setUserId(1);
        mapper.insertUseGeneratedKeys(order);

        // 同一个sqlSession 第二次查询会直接返回一级缓存中的数据
        List<Order> orderList2 = mapper.queryByOrderType("1");
        for (Order order2 : orderList2) {
            System.out.println(order2.toString());
        }
    }

    /**
     * mybatis 的二级缓存
     *
     * <li> 二级缓存存在于sqlSessionFactory的生命周期，可以理解为跨sqlSession的，
     * 缓存是以namespace为单位，不同的namespace下的操作互不影响
     * <li>开启二级缓存需要在mybatis.xml 中设置 cacheEnable = true，并且在对应的namespace的xml中需要增加<cache></cache>
     * <li> 二级缓存容易产生脏读，不建议使用
     */
    @Test
    public void queryOrderUseSqlSessionFactoryCache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderCacheMapper mapper = sqlSession.getMapper(OrderCacheMapper.class);

        // 同一个sqlSession 第一次查询
        List<Order> orderList = mapper.queryByOrderType("1");
        for (Order order : orderList) {
            System.out.println(order.toString());
        }

        // 同一个sqlSession 第二次查询会直接返回一级缓存中的数据
        List<Order> orderList2 = mapper.queryByOrderType("1");
        for (Order order : orderList2) {
            System.out.println(order.toString());
        }
    }
}
