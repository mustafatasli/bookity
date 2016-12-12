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
import com.bookity.domain.BookStock;
import com.bookity.domain.Cart;
import com.bookity.domain.CartItem;
import com.bookity.domain.Customer;
import com.bookity.services.BookService;
import com.bookity.services.CustomerService;
import com.bookity.services.CartService;
import com.bookity.services.BookStockService;


@Controller
@RequestMapping("/api/cart")
public class CartApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CartApiController.class);

  private BookService bookService;
  private CartService cartService;
  private CustomerService customerService;
  private BookStockService stockService;

  @Autowired
  public CartApiController(CartService cartService, CustomerService customerService,
    BookService bookService, BookStockService stockService) {
    this.bookService = bookService;
    this.cartService = cartService;
    this.customerService = customerService;
    this.stockService = stockService;
  }


}