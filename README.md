## Antes de iniciar a aplicação

1 - Verificar as configurações de coneção com o PostgreSQL, no arquivo application.yml na pasta resources verifque a URL, User e Password.

2 - Faça o build da aplicação com o seguinte comando :
```shell
mvn clean && mvn install -DskipTests
```

## Iniciando a aplicação
Para iniciar a aplicação use o seguinte comando:
```shell
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Testes
Para executar os testes unitários e integrados execute o seguinte comando:
```shell
mvn verify
```

### Collection Postman
Collection do Postman para facilitar os testes manuais da aplicação.
[Desafio Hotel.postman_collection.json](https://github.com/user-attachments/files/17348147/Desafio.Hotel.postman_collection.json)

## Informações sobre a aplicação

A aplicação foi desenvolvida usando:

- Java 17;
- Spring-boot 3.3.4;
- Maven 3.9.9;
- PostgreSQL;

As dependências utilizadas forma:

- spring-boot-starter-data-jpa;
- spring-boot-starter-security;
- spring-boot-starter-validation;
- testcontainers;
- org.testcontainers/postgresql;
- postgresql;
- lombok;
