package com.kld.springbootjpa.dao;

import com.kld.springbootjpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Long> {

    @Query("from Customer")
    public List<Customer> findAllCustomers();
}
