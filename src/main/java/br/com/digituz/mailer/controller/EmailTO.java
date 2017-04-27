package br.com.digituz.mailer.controller;

import br.com.digituz.mailer.model.Attachment;
import br.com.digituz.mailer.model.Email;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
class EmailTO {
	private String title;
	private String message;
	private List<AttachmentTO> attachments;
	private List<String> recipients;

	Email toEmail() {
		List<Attachment> attachs = null;
		if (attachments != null) {
			attachs = attachments.stream()
					.map(AttachmentTO::toAttachment)
					.collect(Collectors.toList());
		}
		return new Email(title, message, attachs, recipients);
	}
}
