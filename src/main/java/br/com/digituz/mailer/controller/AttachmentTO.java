package br.com.digituz.mailer.controller;

import br.com.digituz.mailer.model.Attachment;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;

@Data
class AttachmentTO {
	private String filename;
	private String data;

	Attachment toAttachment() {
		return new Attachment(filename, Base64.decodeBase64(data));
	}
}