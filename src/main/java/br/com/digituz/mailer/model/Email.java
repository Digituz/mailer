package br.com.digituz.mailer.model;

import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author daniel
 */
@Entity
@Data
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String message;

	@ElementCollection
	private List<Attachment> attachment;

	@ElementCollection
	private Set<String> recipients;
}