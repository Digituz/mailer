package br.com.digituz.mailer.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
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
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmailRepository emailRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void sendEmails(Email email) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			String[] recipients = email.getRecipients()
					.stream()
					.map(String::new)
					.toArray(String[]::new);

			helper.setTo(recipients);
			helper.setSubject(email.getTitle());
			helper.setText(email.getMessage());
			helper.setFrom("email@example.com.br");

			File fileAttachment = new File("pathAttachment");
			email.setAttachment(fileAttachment);
			Optional<File> attachment = Optional.ofNullable(email.getAttachment());

			if (attachment.isPresent()) {
				helper.addAttachment("attachmentFilename", attachment.get());
			}

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
