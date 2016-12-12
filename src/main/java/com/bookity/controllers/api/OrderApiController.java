package com.bookity.controllers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

import java.util.List;

import com.bookity.domain.Order;
import com.bookity.domain.OrderItem;
import com.bookity.domain.Customer;
import com.bookity.services.OrderService;
import com.bookity.services.CustomerService;



@Controller
@RequestMapping("/api/orders")
public class OrderApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderApiController.class);

  private CustomerService customerService;
  private OrderService orderService;

  @Autowired
  public OrderApiController(OrderService orderService, CustomerService customerService) {
    this.customerService = customerService;
    this.orderService = orderService;
  }

}