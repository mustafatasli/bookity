package com.bookity.domain.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookity.domain.Order;
import com.bookity.domain.Customer;


public interface OrderRepository extends JpaRepository<Order, Long> {

}