package com.leofee;

import com.leofee.dao.UserDao;
import com.leofee.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author leofee
 * @Date: 2019/1/9
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-*.xml");
        UserDao userDao = (UserDao) context.getBean("userDaoImpl");

        User param = new User();
        param.setId(5);
        User user = userDao.loadEntity(param);

        System.out.println(user == null ? "no result":user.toString());

    }
}
