package com.bookity.services;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookity.domain.Order;
import com.bookity.domain.OrderItem;
import com.bookity.domain.CartItem;
import com.bookity.domain.Customer;
import com.bookity.domain.Book;
import com.bookity.domain.repository.OrderRepository;


@Service
public class OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	private OrderRepository repository;

	@Autowired
	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}

	public Order save(Order order) {
		return repository.save(order);
	}

	public Order get(Long id) {
		return repository.findOne(id);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public List<Order> findByCustomer(Customer customer) {
		return repository.findAll();
	}

	public Order createOrder(Customer customer, List<CartItem> items) {
		LOGGER.info("createOrder " + customer.getName());
		Order order = new Order();
		order.setCustomer(customer);
		order.setStatus("created");

		repository.save(order);

		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		OrderItem orderItem = null;
		Book book = null;

		for(CartItem item : items) {
			orderItem = new OrderItem();
			book = item.getBook();
			LOGGER.info("Book " + book.getId());

			orderItem.setOrder(order);
			orderItem.setBookTitle(book.getTitle());
			orderItem.setBookIsbn(book.getIsbn());
			orderItem.setUnitPrice(item.getUnitPrice());
			orderItem.setQuantity(item.getQuantity());

			orderItems.add(orderItem);
		}

		order.setTotal(new BigDecimal(100.0));
		order.setItems(orderItems);

		return repository.save(order);

	}

}