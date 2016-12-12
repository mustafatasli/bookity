package com.bookity;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;


@SpringBootApplication
public class BookityApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(BookityApplication.class, args);
	}

   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      super.addViewControllers(registry);

      registry.addViewController("/login");
   }

   @Bean
   public SpringSecurityDialect securityDialect() {
       return new SpringSecurityDialect();
   }
}
