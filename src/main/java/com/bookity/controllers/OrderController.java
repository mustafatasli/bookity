package com.bookity.controllers;

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
@RequestMapping("/orders")
public class OrderController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

  private CustomerService customerService;
  private OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService, CustomerService customerService) {
    this.customerService = customerService;
    this.orderService = orderService;
  }

  @RequestMapping(method=RequestMethod.GET)
  public String orderList(Model model) {
    Customer customer = customerService.getAuthCustomer();

    List<Order> orders = customer.getOrders();
    model.addAttribute("items", orders);

    return "orderList";
  }

  @RequestMapping(value="/detail", method=RequestMethod.GET)
  public String orderDetail(@RequestParam("id") Long orderId, Model model) {
    Customer customer = customerService.getAuthCustomer();

    LOGGER.info("Order Detail of customer " + customer.getName());

    Order order = orderService.get(orderId);

    if (order != null) {
      LOGGER.info("Getting order items");
      model.addAttribute("items", order.getItems());
    }

    return "orderDetail";
  }

}