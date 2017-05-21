package br.com.digituz.mailer.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Getter;

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
	private int attempts;

	@Enumerated(EnumType.STRING)
	@Column(name = "email_status")
	private EmailStatus emailStatus = EmailStatus.NEW;

	@ElementCollection
	@CollectionTable(
			name = "attachment",
			joinColumns = @JoinColumn(name = "email_id")
	)
	private Set<Attachment> attachments;

	@ElementCollection
	private List<String> recipients;

	protected Email() { }

	public Email(String title, String message,Set<Attachment> attachments, List<String> recipients) {
		this.title = title;
		this.message = message;
		this.attachments = attachments;
		this.recipients = recipients;
	}
}