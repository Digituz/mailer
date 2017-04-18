package br.com.digituz.mailer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.digituz.mailer.model.Email;
import br.com.digituz.mailer.model.Message;
import br.com.digituz.mailer.service.SendEmailService;

/**
 * @author daniel
 */
@RestController
public class SendEmailController {

	@Autowired
	private SendEmailService sendEmailService;

	@PostMapping("/api/send-email")
	@ResponseStatus(value = HttpStatus.OK)
	public Message sendEmails(@RequestBody Email email) {
		this.sendEmailService.sendEmails(email);
		return new Message("Information successfully received.");
	}

	@GetMapping("/api/get-email")
	public List<Email> getEmails() {
		System.out.println("consutando todos");
		return this.sendEmailService.emailsAll();
	}
}