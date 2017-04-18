package br.com.digituz.mailer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digituz.mailer.model.Email;

/**
 * @author daniel
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
}