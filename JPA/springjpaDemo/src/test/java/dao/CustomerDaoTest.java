package dao;

import entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDaoImpl customerDao;

    @Test
    @Transactional
    @Rollback(false)
    public void test(){
        Customer customer = new Customer();
        customer.setCustName("孔令迪");
        customerDao.save(customer);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void test2(){
        Customer customer = new Customer();
        customer.setCustId(2L);
        customer.setCustName("孔令迪222");
        customerDao.update(customer);
    }
}
