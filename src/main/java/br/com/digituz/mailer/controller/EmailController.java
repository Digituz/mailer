package br.com.digituz.mailer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.digituz.mailer.model.Email;
import br.com.digituz.mailer.model.Message;
import br.com.digituz.mailer.service.EmailService;

/**
 * @author daniel
 */
@RestController
@RequestMapping("/api")
public class EmailController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmailService sendEmailService;

	@PostMapping("/send-email")
	@ResponseStatus(value = HttpStatus.OK)
	public Message sendEmails(@RequestBody Email email) {
		this.sendEmailService.sendEmails(email);
		return new Message("Information successfully received.");
	}

	@GetMapping("/get-email")
	public List<Email> getEmails() {
		logger.info("Emails listed");
		return this.sendEmailService.emailsAll();
	}
}