package br.com.digituz.mailer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digituz.mailer.model.Emails;

/**
 * @author daniel
 */
@Repository
public interface MailerRepository extends JpaRepository<Emails, Integer>{
}