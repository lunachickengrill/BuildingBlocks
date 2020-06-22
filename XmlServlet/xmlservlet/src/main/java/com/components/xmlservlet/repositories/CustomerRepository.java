package com.components.xmlservlet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.components.xmlservlet.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public long count();

	public void delete(Customer entity);

	public <S extends Customer> S saveAndFlush(S entity);

	public Optional<Customer> findById(Long id);

	public Optional<Customer> findByEmailAddress(String emailAddress);

	public <S extends Customer> List<S> saveAll(Iterable<S> entities);

}
