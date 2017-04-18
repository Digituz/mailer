package br.com.digituz.mailer.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author daniel
 */
@Entity
public class Emails {

	@Id
	@GeneratedValue
	private int id;

	private String title;
	private String message;

	@ElementCollection
	private List<String> recipients;

	public Emails() {
	}

	public Emails(String title, String message, List<String> recipients) {
		super();
		this.title = title;
		this.message = message;
		this.recipients = recipients;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Emails [id=" + id + ", title=" + title + ", message=" + message + ", recipients=" + recipients
				+ "]";
	}
}