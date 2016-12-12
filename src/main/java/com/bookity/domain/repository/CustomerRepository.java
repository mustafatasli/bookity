package com.bookity.domain.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookity.domain.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query("FROM Customer")
  List<Customer> listAll();
  Customer getByName(String name);

}