package com.bookity;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;


@SpringBootApplication
public class BookityApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BookityApplication.class, args);
	}

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(BookityApplication.class);
  }

  @Bean
  public SpringSecurityDialect securityDialect() {
    return new SpringSecurityDialect();
  }
}