package com.portal.customerreactiveservice.dao;

import java.util.List;
import com.portal.customerreactiveservice.entity.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import com.portal.customerreactiveservice.repo.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository  customerRepository;
	
	private  List<Customer> customerList=List.of(new Customer(null,"Peter","pan"),
			new Customer(null,"John","Smith"),new Customer(null,"Rebeca","patterson"),
			new Customer(null,"William","John"));
	
	@BeforeEach
	void setUp(){
		customerList=customerRepository.deleteAll()
				.thenMany(Flux.fromIterable(customerList))
				.flatMap(customerRepository::save)
				.collectList().block();
		
	}
	
	@Test
	public void getAllCustomer() {
		customerRepository.findAll()
		.doOnNext(System.out::println)
		.as(StepVerifier::create)
		.expectNextCount(4)
		.verifyComplete();
	}
	@Test
	public void getCustomerById() {
		
		customerRepository.findById(customerList.get(2).id())
		.doOnNext(System.out::println)
		.as(StepVerifier::create)
		.expectNextMatches(c->c.firstname().equals("Rebeca"))
		.verifyComplete();
	}
	
	@Test
	public void getCustomerByLastname() {
		String lastname="Smith";
		customerRepository.findByLastname(lastname)
		.doOnNext(System.out::println)
		.as(StepVerifier::create)
		.expectNextMatches(c->c.firstname().equals("John"))
		.verifyComplete();
	}
	@Test
	public void addCustomer() {
		Customer customer=new Customer(null,"Scarlet","Johnson");
		customerRepository.save(customer)
		.doOnNext(System.out::println)
		.as(StepVerifier::create)
		.expectNextMatches(c->c.firstname().equals("Scarlet"))
		.verifyComplete();
	}
	
	@Test
	public void updateCustomer() {
		Customer updateCustomer=new Customer(customerList.get(0).id(),"Peter","Pan Senior");
		customerRepository.save(updateCustomer)
		.doOnNext(System.out::println)
		.as(StepVerifier::create)
		.expectNextMatches(c->c.firstname().equals("Peter"))
		.verifyComplete();
	}
	@Test
	public void deleteCustomer() {
		
		customerRepository.deleteById(customerList.get(1).id())
		.doOnNext(System.out::println)
		.as(StepVerifier::create)
		.verifyComplete();
	}
}
