CREATE TABLE email_recipients
(
  email_id integer NOT NULL,
  recipients varchar (255),
  CONSTRAINT email_recipients_fk FOREIGN KEY (email_id)
      REFERENCES email (id)
);