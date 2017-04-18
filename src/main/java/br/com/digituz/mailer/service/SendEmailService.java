package br.com.digituz.mailer.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.digituz.mailer.model.Email;
import br.com.digituz.mailer.repository.EmailRepository;

/**
 * @author daniel
 */
@Service
public class SendEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmailRepository emailRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void sendEmails(Email email) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			InternetAddress[] address = new InternetAddress[email.getRecipients().size()];
			for (int i = 0; i < email.getRecipients().size(); i++) {
				address[i] = new InternetAddress(email.getRecipients().get(i));
			}

			helper.setTo(address);
			helper.setSubject(email.getTitle());
			helper.setFrom("email@example.com");
			helper.setText(email.getMessage());

			javaMailSender.send(mimeMessage);
			logger.info("Email sent");

		} catch (MessagingException e) {
			logger.error(e.getMessage(), e);
		}
		this.emailRepository.save(email);
	}

	public List<Email> emailsAll() {
		return this.emailRepository.findAll();
	}
}