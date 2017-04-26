create table email
(
  id bigserial primary key not null,
  message varchar (255),
  title varchar (255)
);

create table email_recipients
(
  email_id bigserial not null,
  recipients varchar (255),
  CONSTRAINT email_recipients_fk FOREIGN KEY (email_id) 
  REFERENCES email (id)
);

create table email_attachment
(
  email_id bigserial not null,
  filename varchar (255),
  data varchar (255),
  CONSTRAINT email_attachment_fk FOREIGN KEY (email_id) 
  REFERENCES email (id)
);