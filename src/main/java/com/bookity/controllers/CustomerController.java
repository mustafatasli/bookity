package com.bookity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;

import java.util.List;
import javax.validation.Valid;

import com.bookity.domain.Customer;
import com.bookity.services.CustomerService;


@Controller
@RequestMapping("/customers")
public class CustomerController {

  private CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping(method=RequestMethod.GET)
  public String customers(Model model) {
    List<Customer> customers = customerService.listAll();

    if (customers != null) {
      model.addAttribute("customers", customers);
    }

    return "customerList";
  }

  @RequestMapping(value="/signup", method=RequestMethod.GET)
  public String signUpForm(Model model) {
    model.addAttribute("user", new Customer());
    return "signUp";
  }

  @RequestMapping(value="/signup", method=RequestMethod.POST)
  public String signUpUser(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
    customerService.save(customer);
    return "redirect:/books";
  }

}