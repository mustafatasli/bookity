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
import com.bookity.domain.Customer;
import com.bookity.domain.Cart;

import com.bookity.services.BookService;
import com.bookity.services.CustomerService;
import com.bookity.services.CartService;

@Controller
@RequestMapping("/")
public class BookListController {

  private static final Logger LOGGER = LoggerFactory.getLogger(BookListController.class);

  private BookService bookService;
  private CustomerService customerService;
  private CartService cartService;

  @Autowired
  public BookListController(BookService bookService, CartService cartService, CustomerService customerService) {
    this.bookService = bookService;
    this.customerService = customerService;
    this.cartService = cartService;
  }

  @RequestMapping(value={"/", "/books"}, method=RequestMethod.GET)
  public String listBooks(Model model) {

    Customer customer = customerService.getAuthCustomer();

    if (customer != null) {
      LOGGER.info("Customer: " + customer.getName());
      Cart cart = cartService.getByCustomer(customer);
      LOGGER.info("Cart " + cart.getId());
    }

    List<Book> bookList = bookService.listAll();

    if (bookList != null) {
      model.addAttribute("books", bookList);
    }

    model.addAttribute("book", new Book());

    return "bookList";
  }

  @RequestMapping(value="/detail", method=RequestMethod.GET)
  public String detailBook(@RequestParam("id") Long id, Model model) {

    Book book = bookService.get(id);
    model.addAttribute("book", book);

    LOGGER.info("Detail of Book ID: " + book.getId());

    return "detailBook";
  }

  @RequestMapping(value="/addbook", method=RequestMethod.GET)
  public String addBook(Model model) {

    model.addAttribute("book", new Book());

    return "addBook";
  }

  @RequestMapping(value="/update", method=RequestMethod.GET)
  public String updateBookForm(@RequestParam("id") Long id, Model model) {

    Book book = bookService.get(id);
    model.addAttribute("book", book);

    LOGGER.info("Book ID: " + book.getId());

    return "editBook";
  }

  @RequestMapping(value="/update", method=RequestMethod.POST)
  public String updateBook(@Valid @ModelAttribute Book book) {

    LOGGER.info("Updating Book ID: " + book.getId());

    bookService.save(book);

    return "redirect:/books";
  }

  @RequestMapping(value="/addbook", method=RequestMethod.POST)
  public String addBook(@Valid @ModelAttribute Book book, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
        List<Book> bookList =bookService.listAll();

        if (bookList != null) {
          model.addAttribute("books", bookList);
        }
        model.addAttribute("book", book);
        return "bookList";
    }

    bookService.save(book);
    return "redirect:/books";
  }

  @RequestMapping(value="/delete", method=RequestMethod.GET)
  public String deleteBook(@RequestParam("id") Long id, Model model) {

    LOGGER.info("Deleting Book ID: " + id);
    bookService.delete(id);

    return "redirect:/books";
  }

}