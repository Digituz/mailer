package br.com.digituz.mailer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.digituz.mailer.model.Emails;
import br.com.digituz.mailer.service.SendEmailService;

/**
 * @author daniel
 */
@RestController
public class SendEmailController {

	@Autowired
	private SendEmailService sendEmailService;

	@PostMapping("/api/send-email")
	public String sendEmails(@RequestBody Emails emails) {
		this.sendEmailService.sendEmails(emails);
		return new ResponseEntity<>("Information received successfully!", HttpStatus.OK).getBody();
	}

	@GetMapping("/api/get-email")
	public List<Emails> getTarefas() {
		System.out.println("consutando todos");
		return this.sendEmailService.listarTarefas();
	}
}