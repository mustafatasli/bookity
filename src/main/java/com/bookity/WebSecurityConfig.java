package com.bookity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bookity.domain.repository.CustomerRepository;
import com.bookity.services.CustomerService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("test").password("12345").roles("USER");
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurity extends WebSecurityConfigurerAdapter {
        @Autowired
        private CustomerRepository customerRepository;

        @Override
        public UserDetailsService userDetailsServiceBean() throws Exception {
            return new CustomerService(customerRepository);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsServiceBean());
        }

        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/api/**")
                .authorizeRequests()
                    .anyRequest().hasRole("USER")
                    .and()
                .csrf().disable()
                .httpBasic();
        }
    }

    @Configuration
    public static class FormLoginWebSecurity extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomerRepository customerRepository;

        @Override
        public UserDetailsService userDetailsServiceBean() throws Exception {
            return new CustomerService(customerRepository);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsServiceBean());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/cart").authenticated()
                    .antMatchers("/edit").authenticated()
                    .antMatchers("/addbook").authenticated()
                    .antMatchers("/update").authenticated()
                    .antMatchers("/delete").authenticated()
                    .antMatchers("/orders").authenticated()
                    .antMatchers("/orders/**").authenticated()
                    .antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
        }
    }
}
