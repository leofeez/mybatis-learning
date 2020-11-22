import com.mybatis.mapper.OrderMapper;
import com.mybatis.mapper.UserMapper;
import com.pojo.Order;
import com.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MyBatisMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(Resources.getResourceAsStream("mybatis.xml"));
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
}
