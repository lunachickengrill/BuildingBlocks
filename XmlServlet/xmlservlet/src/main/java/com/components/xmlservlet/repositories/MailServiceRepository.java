package com.components.xmlservlet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.components.xmlservlet.model.MailService;

@Repository
public interface MailServiceRepository extends JpaRepository<MailService, Long> {

	public long count();

	public void delete(MailService entity);

	public <S extends MailService> S saveAndFlush(S entity);

	public Optional<MailService> findById(Long id);

	public Optional<MailService> findByEmailAddress(String emailAddress);

	public <S extends MailService> List<S> saveAll(Iterable<S> entities);

}
