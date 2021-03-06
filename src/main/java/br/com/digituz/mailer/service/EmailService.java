package br.com.digituz.mailer.service;

import br.com.digituz.mailer.model.Attachment;
import br.com.digituz.mailer.model.Email;
import br.com.digituz.mailer.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @author daniel
 */
@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmailRepository emailRepository;

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${mailer.replyTo}")
	private String replyTo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void sendEmails(Email email) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			String[] recipients = email.getRecipients()
					.stream()
					.map(String::new)
					.toArray(String[]::new);

			helper.setFrom(sender);
			helper.setTo(recipients);
			helper.setSubject(email.getTitle());
			helper.setText(email.getMessage());
			helper.setReplyTo(replyTo);

			for (Attachment attachment : email.getAttachments()) {
				helper.addAttachment(attachment.getFilename(), new ByteArrayResource(attachment.getData()));
			}

			javaMailSender.send(mimeMessage);
			logger.info("Email sent");

			this.emailRepository.save(email);

		} catch (MessagingException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public List<Email> emailsAll() {
		return this.emailRepository.findAll();
	}
}
