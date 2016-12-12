package com.bookity.services;

import java.util.List;
import java.util.Random;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookity.domain.payment.CardInfo;
import com.bookity.domain.payment.PaymentResult;

import com.bookity.domain.repository.BookRepository;


@Service
public class PaymentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);


	public PaymentResult charge(CardInfo cardInfo, BigDecimal total) {
		Random rand = new Random();
		boolean succeeded = rand.nextBoolean();
		String refID = Integer.toString(rand.nextInt());

		return new PaymentResult(succeeded, refID, "request", "response");
    }

}