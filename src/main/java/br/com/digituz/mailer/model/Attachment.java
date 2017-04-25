package br.com.digituz.mailer.model;

import lombok.Data;

/**
 * @author daniel
 */
@Data
public class Attachment {

	private String filename;
	private String data;
}