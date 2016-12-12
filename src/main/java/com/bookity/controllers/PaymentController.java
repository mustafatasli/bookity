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
import javax.validation.Valid;

import com.bookity.domain.Book;
import com.bookity.domain.Cart;
import com.bookity.domain.CartItem;
import com.bookity.domain.Customer;
import com.bookity.domain.payment.CardInfo;
import com.bookity.domain.payment.PaymentResult;
import com.bookity.domain.Order;

import com.bookity.services.BookService;
import com.bookity.services.CustomerService;
import com.bookity.services.CartService;
import com.bookity.services.BookStockService;
import com.bookity.services.PaymentService;
import com.bookity.services.OrderService;


@Controller
@RequestMapping("/payment")
public class PaymentController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

  private CartService cartService;
  private CustomerService customerService;
  private BookStockService stockService;
  private PaymentService paymentService;
  private OrderService orderService;

  @Autowired
  public PaymentController(CartService cartService, BookStockService stockService,
      CustomerService customerService, PaymentService paymentService, OrderService orderService) {
    this.cartService = cartService;
    this.customerService = customerService;
    this.stockService = stockService;
    this.paymentService = paymentService;
    this.orderService = orderService;
  }

  @RequestMapping(method=RequestMethod.GET)
  public String checkoutForm(Model model) {
    Customer customer = customerService.getAuthCustomer();

    LOGGER.info("Cart Detail of customer " + customer.getName());
    Cart cart = cartService.getByCustomer(customer);

    if (cart != null) {
      LOGGER.info("Getting cartitems");
      List<CartItem> items = cart.getItems();
      stockService.updateBookStocks(items);
      model.addAttribute("cart", cart);
      model.addAttribute("items", items);
      model.addAttribute("total", cartService.getTotal(items));
    }

    return "payment";
  }


  @RequestMapping(value="/complete", method=RequestMethod.POST)
  public String completePayment(
      @RequestParam("number") String cardNumber,
      @RequestParam("name") String name,
      @RequestParam("expire") String expiresAt) {

    Customer customer = customerService.getAuthCustomer();
    CardInfo cardInfo = new CardInfo(cardNumber, name, expiresAt);

    Cart cart = cartService.getByCustomer(customer);
    List<CartItem> items = cart.getItems();

    Order order = orderService.createOrder(customer, items);

    LOGGER.info(String.format("Charging Customer:%d Order:%d",
                              customer.getId(), order.getId()));

    PaymentResult result = paymentService.charge(cardInfo, order.getTotal());

    if (result.succeeded) {
      order.setStatus("completed");
    }
    else {
      order.setStatus("failed");
      return "redirect:/payment";
    }

    cartService.delete(cart);
    orderService.save(order);

    return "redirect:/orders";
  }


}