package br.com.digituz.mailer.model;

import javax.persistence.Embeddable;

import lombok.Data;

/**
 * @author daniel
 */
@Data
@Embeddable
public class Attachment {

	private String filename;
	private String data;
}