# Vibbra Challenge

### Checklist delivery
1. Já avaliei o escopo (tirei as dúvidas com cliente ou equipe técnica, ou não tive
dúvidas).
2. Fiz uma estimativa em HORAS do desenvolvimento de TODO o projeto.
Também fiz uma estimativa em DIAS do prazo de entrega.
3. Já tenho o link da minha tabela com as horas que utilizei apontadas.
4. Realizei as atividades do Must Have, todas estão desenvolvidas e funcionais, com
código no repositório gitlab fornecido.
5. Organizei todas as informações acima no Read.me do repositório.

### The application

The application was developed using Spring Boot 2.0.5 along with the starters JPA, WEB and Security. Spring Fox was also used to provide Rest API documentation.

### Running the application

The application can be started in three different ways as follow.

1. Clone this project, import it in a Java IDE and Run the class VibbraChallengeApplication.java
2. Clone this project, execute a maven clean install using the command 'mvn clean install'. Access the generated target folder and execute the command 'java -jar vibbra-challenge-1.0-SNAPSHOT.jar'
3. Clone this project, access de project folder and run the command 'mvn spring-boot:run'

### Access to the application

After the application has started, the link to access the services will be.

[http://localhost:8080/vibbra/](http://localhost:8080/vibbra/)

The list of available endpoints can be checked in the next session. For endpoints that needs authorization, the user available is vibbra, the password will randomicaly generated and ploted in the log during the application's initialization, example:

```
Using generated security password: 5ed1a33f-063e-4350-a419-13e2e837ff2c
```

The password must be used along with the user vibbra, in your rest client, use the option "Basic Auth" and fill the username and password with the information provided.

### Rest API Documentation

To access the Rest Api Documentation provided by the application, use the link bellow.

[http://localhost:8080/vibbra/swagger-ui.html](http://localhost:8080/vibbra/swagger-ui.html)