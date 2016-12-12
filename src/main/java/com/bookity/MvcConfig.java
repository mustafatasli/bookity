package com.bookity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      super.addViewControllers(registry);

      registry.addViewController("/login");
   }

}