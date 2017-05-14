package br.com.digituz.mailer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.digituz.mailer.model.Email;
import br.com.digituz.mailer.model.EmailStatus;

/**
 * @author daniel
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

	@Query("select  e from Email e join fetch e.attachments join fetch e.recipients where e.emailStatus in ?1")
	public List<Email> getUnsentEmails(List<EmailStatus> emailStatus);
}