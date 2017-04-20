# Mailer
Mailer é um microserviço para lidar com o processo de envio de e-mails

Basicamente esté microserviço captura os parametros passados por uma requisão POST informando as seguintes informações :

 * Os destinatários
 * O título do email
 * O corpo do email 


Antes de executar, a aplicação deve ser configurada da seguinte maneira:

- Criar um banco de dados no Postgresql chamado `mailer`.
- Alterar o arquivo `application.properties` que fica dentro `src/main/resources`,
informando as informações do `provedor de email` e as de conexão com o `banco de dados``.
- Informar o email que será usado para enviar para os destinatários na classe `EmailService.java` no seguinte trecho `helper.setFrom("email@example.com");`  que está localizado no pacote : `br.com.digituz.mailer.service`.
- Execute a classe `MailerApplication.java` localizada no pacote `br.com.digituz.mailer`
- Após a execução a aplicação ira criar `2 tabelas` no banco de dado chamada `email` e `email_recipients` através do FlyWayDB.


### Os recursos disponíveis:

* **GET**  http://localhost:8080/api/get-email 
* **POST** http://localhost:8080/api/send-email

### Testando os recursos:
- Com o `curl` que é um termimal para transferir dados para varios protocolos.
- Para testar o POST passamos as seguinte informações no terminal:

	curl -H "Content-Type: application/json" -X POST -d 
    '{ "title":"Olá Mundo !",
       "message":"Meu Primeiro Email.",
       "recipients":["example@example.com","example@example.com.br"]
    }'  http://localhost:8080/api/send-email 

- Para testar o GET usamos o seguinte comando para listar os dados:

	curl -H "Content-Type: application/json" -X GET http://localhost:8080/api/get-email    

