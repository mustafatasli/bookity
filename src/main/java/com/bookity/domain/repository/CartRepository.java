package com.bookity.domain.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookity.domain.Cart;
import com.bookity.domain.CartItem;
import com.bookity.domain.Customer;


public interface CartRepository extends JpaRepository<Cart, Long> {


}