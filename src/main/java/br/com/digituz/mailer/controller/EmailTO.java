package br.com.digituz.mailer.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.digituz.mailer.model.Attachment;
import br.com.digituz.mailer.model.Email;
import lombok.Data;

@Data
class EmailTO {
	private String title;
	private String message;
	private Set<AttachmentTO> attachments;
	private List<String> recipients;

	Email toEmail() {
		Set<Attachment> attachs = null;
		if (attachments != null) {
			attachs = attachments.stream()
					.map(AttachmentTO::toAttachment)
					.collect(Collectors.toSet());
		}
		return new Email(title, message, attachs, recipients);
	}
}