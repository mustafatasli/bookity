package com.bookity.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bookity.domain.Customer;
import com.bookity.domain.repository.CustomerRepository;


@Service
public class CustomerService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public Customer save(Customer customer) {
		return repository.save(customer);
	}

	public List<Customer> listAll() {
		return repository.listAll();
	}

	public Customer getAuthCustomer() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();
		return repository.getByName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Customer customer = repository.getByName(username);

		if (customer == null) {
			LOGGER.info("User Not found");
			throw new UsernameNotFoundException(username + " not found");
		}

		LOGGER.info("Found");

		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		UserDetails userDetails = new User(
			customer.getName(),
			customer.getPassword(),
			Arrays.asList(authority));
		return userDetails;
	}

}