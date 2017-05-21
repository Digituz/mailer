package br.com.digituz.mailer.service;

import java.util.Arrays;
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

		List<Email> emails = this.emailRepository.getUnsentEmails(Arrays.asList(EmailStatus.NEW, EmailStatus.ERROR));

		emails.forEach(s -> {
			try {
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				String[] recipients = s.getRecipients().stream().map(String::new).toArray(String[]::new);

				helper.setText(s.getMessage());
				helper.setFrom(sender);
				helper.setTo(recipients);
				helper.setSubject(s.getTitle());
				helper.setReplyTo(replyTo);

				for (Attachment attachment : s.getAttachments()) {
					helper.addAttachment(attachment.getFilename(), new ByteArrayResource(attachment.getData()));
				}

				javaMailSender.send(mimeMessage);
				
			} catch (MessagingException e) {
				logger.error(e.getMessage(), e);
			}

		});

	}

	public List<Email> emailsAll() {
		return this.emailRepository.findAll();
	}
}