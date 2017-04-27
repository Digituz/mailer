package br.com.digituz.mailer.model;

import lombok.Getter;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.List;

/**
 * @author daniel
 */
@Entity
@Getter
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String message;

	@ElementCollection
	@CollectionTable(
			name = "attachment",
			joinColumns = @JoinColumn(name = "email_id")
	)
	private List<Attachment> attachments;

	@ElementCollection
	private List<String> recipients;

	protected Email() { }

	public Email(String title, String message, List<Attachment> attachments, List<String> recipients) {
		this.title = title;
		this.message = message;
		this.attachments = attachments;
		this.recipients = recipients;
	}
}
