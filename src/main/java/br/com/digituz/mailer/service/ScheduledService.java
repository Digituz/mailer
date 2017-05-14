package br.com.digituz.mailer.service;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author daniel
 */
@Component
public class ScheduledService {

	@Autowired
	private EmailService emailService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron = "*/1000 * * * * *")
	public void sendEmails() throws MessagingException {

		emailService.sendEmails();
		logger.info("Scheduled Email Sents");
	}
}