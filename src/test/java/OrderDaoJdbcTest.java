import com.jdbc.OrderDao;
import com.pojo.Order;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class OrderDaoJdbcTest {

    @Test
    public void test() {
        OrderDao orderDao = new OrderDao();
        List<Order> orders = orderDao.queryOrderByOrderType("1");
        System.out.println(Arrays.toString(orders.toArray()));
    }
}
