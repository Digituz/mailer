# Mailer

Mailer is a microservice that deals with the process of sending email messages. Its goals is to tkae out the burden of the client, assuring that the message will be delivered. That is, the client asks Mailer to send a message, Mailer saves this message in the database and tries to send it. In case there is a problem with the process of sending this message, Mailer waits a few minutes and tries again.

In the meanwhile, the client can keep going with its normal flow, knowing that Mailer will deliver it (i.e. if the recipient address exists).

To send an email message through Mailer, the client has to issue a `POST` request to `/api/email` with the following `JSON` structure:

```json
{
    "title":"Hello World",
    "message":"This is a message sent through Mailer",
    "recipients": [
        "example@example.com",
        "example@example.com.br"
    ],
    "attachments": [
        {
            "filename": "some-file.png",
            "data": "ASDAHSDASDASDAHSASDH" // base64 encoded binary data
        }
    ]
}
```

To run the application, do the following:

- Create a `mailer` database on a PostgreSQL instance
- Make a copy of `/profile/${environment}/application.properties.example`, removing the `.example` suffix, and replace the key-values appropriately.


## Testing Mailer Manually

To send an email message through Mailer, issue a `curl` command as follows:

```bash
curl -H "Content-Type: application/json" -X POST -d
'{ "title":"Olá Mundo !",
   "message":"Meu Primeiro Email.",
   "recipients":["example@example.com","example@example.com.br"]
}'  http://localhost:8080/api/send-email
```
