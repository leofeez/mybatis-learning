import com.spring.entity.Order;
import com.spring.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author leofee
 * @date 2020/11/28
 */
@ContextConfiguration(value = "classpath:applicationContext-Common.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class MyBatisSpringTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void queryOrder() {
        Order order = orderMapper.selectByPrimaryKey(1);
        System.out.println("order = " + order);
    }

}
