create table email (
  id bigserial primary key not null,
  message varchar (255) not null,
  title varchar (255) not null
);

create table email_recipients (
  email_id bigserial not null,
  recipients varchar (255) not null,
  constraint email_recipients_fk foreign key (email_id)
    references email (id)
);

create table attachment (
  filename varchar (255) not null,
  data bytea not null,
  email_id bigserial not null,
  constraint email_recipients_fk foreign key (email_id)
    references email (id)
);
