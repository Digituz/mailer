package br.com.digituz.mailer.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.digituz.mailer.model.Emails;
import br.com.digituz.mailer.repository.MailerRepository;

/**
 * @author daniel
 */
@Service
public class SendEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private MailerRepository mailerRepository;

	public void sendEmails(Emails emails) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			InternetAddress[] address = new InternetAddress[emails.getRecipients().size()];
			for (int i = 0; i < emails.getRecipients().size(); i++) {
				address[i] = new InternetAddress(emails.getRecipients().get(i));
			}

			helper.setTo(address);
			helper.setSubject(emails.getTitle());
			helper.setFrom("email@example.com");
			helper.setText(emails.getMessage());

			javaMailSender.send(mimeMessage);
			System.out.println("Email sent!");

		} catch (MessagingException e) {
			System.err.println(e.getMessage());
		}
		this.mailerRepository.save(emails);
	}

	public List<Emails> listarTarefas() {
		return this.mailerRepository.findAll();
	}
}