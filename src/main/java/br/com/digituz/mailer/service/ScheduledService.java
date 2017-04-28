package br.com.digituz.mailer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author daniel
 */
@Component
public class ScheduledService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${mailer.replyTo}")
	private String replyTo;

	private static final String TIME_ZONE = "America/Sao_Paulo";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron = "* */5 * * * *", zone = TIME_ZONE)
	public void sendEmails() {

//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		try {
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//			String[] recipients = email.getRecipients()
//					.stream()
//					.map(String::new)
//					.toArray(String[]::new);
//
//			helper.setFrom(sender);
//			helper.setTo(recipients);
//			helper.setSubject(email.getTitle());
//			helper.setText(email.getMessage());
//			helper.setReplyTo(replyTo);
//
//			for (Attachment attachment : email.getAttachments()) {
//				helper.addAttachment(attachment.getFilename(),
//						new ByteArrayResource(attachment.getData()));
//			}
//
//			javaMailSender.send(mimeMessage);
//			logger.info("Email sent");
//
//		} catch (MessagingException e) {
//			logger.error(e.getMessage(), e);
//		}
	}
}