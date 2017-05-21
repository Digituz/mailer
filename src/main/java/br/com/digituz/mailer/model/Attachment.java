package br.com.digituz.mailer.model;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Attachment {
	private String filename;

	private byte[] data;

	protected Attachment() { }

	public Attachment(String filename, byte[] data) {
		this.filename = filename;
		this.data = data;
	}
}