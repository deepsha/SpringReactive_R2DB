package com.portal.customerreactiveservice.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.portal.customerreactiveservice.entity.Customer;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long>{

	Flux<Customer>  findByLastname(String lastname);
}
