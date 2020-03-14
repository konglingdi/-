package dao;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class CustomerTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    @Transactional
    @Rollback(false)
    public void test(){
        Customer customer = new Customer();
        customer.setCustName("klddddd");
        customerDao.save(customer);
    }


    @Test
    public void test2(){
        List<Customer> allCustomers = customerDao.findAllCustomers();
        System.out.println(allCustomers);
    }
}
