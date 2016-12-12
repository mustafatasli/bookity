package com.bookity.services;

import java.util.List;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookity.domain.Cart;
import com.bookity.domain.CartItem;
import com.bookity.domain.Customer;
import com.bookity.domain.repository.CartRepository;


@Service
public class CartService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
	private CartRepository repository;

	@Autowired
	public CartService(CartRepository repository) {
		this.repository = repository;
	}

	public Cart save(Cart cart) {
		return repository.save(cart);
	}

	public Cart get(Long id) {
		return repository.findOne(id);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public void delete(Cart cart) {
		repository.delete(cart);
	}

	public Cart getByCustomer(Customer customer) {
		Cart cart = customer.getCart();
		if (cart == null) {
			LOGGER.info("Creating cart");
			cart = new Cart();
			cart.setCustomer(customer);
			repository.save(cart);
		} else {
			LOGGER.info("Cart ID " + cart.getId());
		}

		return cart;
	}

	public BigDecimal getTotal(List<CartItem> items) {
		BigDecimal sum = BigDecimal.ZERO;
	    for(CartItem item : items) {
	        sum = sum.add(item.getStock().getPrice());
	    }

	    return sum;
	}

}