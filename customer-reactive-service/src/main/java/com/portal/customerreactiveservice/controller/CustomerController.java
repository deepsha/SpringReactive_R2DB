package com.portal.customerreactiveservice.controller;
import com.portal.customerreactiveservice.entity.Customer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.portal.customerreactiveservice.repo.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/crm")
public class CustomerController {
	
	private final CustomerRepository repo;

	public CustomerController(CustomerRepository repo) {
		this.repo = repo;
	}
	@GetMapping("/customers")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Flux<Customer>> getAllCustomers()
	{
		Flux<Customer> customerList=repo.findAll();
		HttpStatus status = (customerList != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(customerList, status);
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<Customer>> addCustomer(@RequestBody Customer customer)
	{
		Mono<Customer> customerAdded=repo.save(customer);
		HttpStatus status = (customerAdded != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(customerAdded, status);
	}
	@PutMapping("/edit")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<Customer>> updateCustomer(@RequestBody Customer customer)
	{
		Mono<Customer> customerAdded=repo.save(customer);
		HttpStatus status = (customerAdded != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(customerAdded, status);
	}
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<Void>> deleteCustomer(@PathVariable Long id)
	{
		Mono<Void> customerAdded=repo.deleteById(id);
		HttpStatus status = (customerAdded != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(customerAdded, status);
	}

}
