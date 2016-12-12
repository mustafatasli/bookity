package com.bookity.controllers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import javax.validation.Valid;

import com.bookity.domain.Book;
import com.bookity.domain.Customer;
import com.bookity.domain.Cart;

import com.bookity.services.BookService;
import com.bookity.services.CustomerService;
import com.bookity.services.CartService;


@Controller
@RequestMapping("/api/books")
public class BookListApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(BookListApiController.class);

  private BookService bookService;
  private CustomerService customerService;

  @Autowired
  public BookListApiController(BookService bookService, CustomerService customerService) {
    this.bookService = bookService;
    this.customerService = customerService;
  }

  @RequestMapping(method=RequestMethod.GET, produces="application/json")
  public ResponseEntity<?> list() {

    Customer customer = customerService.getAuthCustomer();

    return new ResponseEntity<List<Book>>(bookService.listAll(), HttpStatus.OK);
  }

  @RequestMapping(
    value="/{id}", method=RequestMethod.GET, produces="application/json")
  public ResponseEntity<?> get(@PathVariable("id") Long id) {

    Book book = bookService.get(id);

    LOGGER.info("Detail of Book ID: " + book.getId());

    return new ResponseEntity<Book>(book, HttpStatus.OK);
  }

  @RequestMapping(method=RequestMethod.POST, consumes="application/json")
  public ResponseEntity<?> post(@RequestBody Book book) {

    LOGGER.info("Updating Book ID: " + book.getId());

    return new ResponseEntity<Book>(bookService.save(book), HttpStatus.CREATED);
  }

  @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {

    LOGGER.info("Deleting Book ID: " + id);

    try {
      bookService.delete(id);
    } catch (RuntimeException e) {
      return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<String>("Deleted", HttpStatus.OK);
  }

}