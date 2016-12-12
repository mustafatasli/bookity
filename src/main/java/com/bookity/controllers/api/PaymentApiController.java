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
import javax.validation.Valid;

import com.bookity.domain.Book;
import com.bookity.domain.Cart;
import com.bookity.domain.CartItem;
import com.bookity.domain.Customer;
import com.bookity.domain.Order;
import com.bookity.domain.payment.CardInfo;
import com.bookity.domain.payment.PaymentResult;

import com.bookity.services.BookService;
import com.bookity.services.CustomerService;
import com.bookity.services.CartService;
import com.bookity.services.BookStockService;
import com.bookity.services.PaymentService;
import com.bookity.services.OrderService;


@Controller
@RequestMapping("/api/payment")
public class PaymentApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentApiController.class);

  private CartService cartService;
  private CustomerService customerService;
  private BookStockService stockService;
  private PaymentService paymentService;
  private OrderService orderService;

  @Autowired
  public PaymentApiController(CartService cartService, BookStockService stockService,
      CustomerService customerService, PaymentService paymentService, OrderService orderService) {
    this.cartService = cartService;
    this.customerService = customerService;
    this.stockService = stockService;
    this.paymentService = paymentService;
    this.orderService = orderService;
  }


}