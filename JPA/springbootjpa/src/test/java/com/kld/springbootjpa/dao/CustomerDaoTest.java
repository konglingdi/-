package com.kld.springbootjpa.dao;

import com.kld.springbootjpa.SpringbootjpaApplication;
import com.kld.springbootjpa.entity.Customer;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootjpaApplication.class)
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void test(){
        List<Customer> allCustomers = customerDao.findAllCustomers();
        System.out.println(allCustomers);
    }
}
