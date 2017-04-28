package br.com.digituz.mailer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digituz.mailer.model.Email;
import br.com.digituz.mailer.repository.EmailRepository;

/**
 * @author daniel
 */
@Service
public class EmailService {

	@Autowired
	private EmailRepository emailRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void saveEmails(Email email) {
		this.emailRepository.save(email);
		logger.info("Email saved");
	}

	public List<Email> emailsAll() {
		return this.emailRepository.findAll();
	}
}