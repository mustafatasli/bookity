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
import com.bookity.domain.BookStock;
import com.bookity.domain.Cart;
import com.bookity.domain.CartItem;
import com.bookity.domain.Customer;
import com.bookity.services.BookService;
import com.bookity.services.CustomerService;
import com.bookity.services.CartService;
import com.bookity.services.BookStockService;


@Controller
@RequestMapping("/cart")
public class CartController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

  private BookService bookService;
  private CartService cartService;
  private CustomerService customerService;
  private BookStockService stockService;

  @Autowired
  public CartController(CartService cartService, CustomerService customerService,
    BookService bookService, BookStockService stockService) {
    this.bookService = bookService;
    this.cartService = cartService;
    this.customerService = customerService;
    this.stockService = stockService;
  }

  @RequestMapping(method=RequestMethod.GET)
  public String cartDetail(Model model) {
    Customer customer = customerService.getAuthCustomer();

    LOGGER.info("Cart Detail of customer " + customer.getName());
    Cart cart = cartService.getByCustomer(customer);
    model.addAttribute("cart", cart);

    if (cart != null) {
      LOGGER.info("Getting cartitems");
      List<CartItem> items = cart.getItems();
      stockService.updateBookStocks(items);

      model.addAttribute("total", cartService.getTotal(items));
      model.addAttribute("items", items);
    }

    return "cartDetail";
  }


  @RequestMapping(value="/update", method=RequestMethod.POST)
  public String updateCart(@RequestParam("id") Long bookId) {
    LOGGER.info("Book ID " + bookId);
    Book book = bookService.get(bookId);

    LOGGER.info("Updating Cart with book ID: " + book.getId());

    Customer customer = customerService.getAuthCustomer();

    Cart cart = cartService.getByCustomer(customer);

    CartItem item = new CartItem();
    item.setBook(book);
    item.setCart(cart);
    cart.addItem(item);
    cartService.save(cart);

    return "redirect:/cart";
  }


}