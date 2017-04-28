package br.com.digituz.mailer.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.digituz.mailer.model.Attachment;
import br.com.digituz.mailer.model.Email;
import br.com.digituz.mailer.model.EmailStatus;
import lombok.Data;

@Data
class EmailTO {
	private String title;
	private String message;
	private List<AttachmentTO> attachments;
	private List<String> recipients;
	private EmailStatus emailStatus;

	Email toEmail() {
		List<Attachment> attachs = null;
		if (attachments != null) {
			attachs = attachments.stream()
					.map(AttachmentTO::toAttachment)
					.collect(Collectors.toList());
		}
		return new Email(title, message, attachs, recipients, emailStatus);
	}
}
