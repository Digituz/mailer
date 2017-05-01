package br.com.digituz.mailer.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.digituz.mailer.model.Attachment;
import br.com.digituz.mailer.model.Email;
import br.com.digituz.mailer.model.EmailStatus;
import br.com.digituz.mailer.repository.EmailRepository;

/**
 * @author daniel
 */
@Service
public class EmailService {

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${mailer.replyTo}")
	private String replyTo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void saveEmails(Email email) {
		this.emailRepository.save(email);
		logger.info("Email saved");
	}

	public void sendEmails() throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		emailsAll()
		.stream()
		.filter(mail -> EmailStatus.NEW.equals(mail.getEmailStatus()))
		.forEach(mail -> {
			String[] recipients = mail.getRecipients()
					.stream()
					.map(String::new)
					.toArray(String[]::new);
			try {
				helper.setFrom(sender);

				helper.setTo(recipients);

				helper.setSubject(mail.getTitle());
				helper.setText(mail.getMessage());
				helper.setReplyTo(replyTo);

				for (Attachment attachment : mail.getAttachments()) {
					helper.addAttachment(attachment.getFilename(),
							new ByteArrayResource(attachment.getData()));
				}

			} catch (MessagingException e) {
				logger.error(e.getMessage(), e);
			}
		});

		javaMailSender.send(mimeMessage);
		logger.info("Email sent");
	}

	public List<Email> emailsAll() {
		return this.emailRepository.findAll();
	}
}