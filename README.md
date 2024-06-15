# Projeto Fintech com mensageria  -API

projeto em Java 17 Spring para simular back-end de uma fintech

-API com Spring Boot

-Entidades com Jakarta Persistence

-Comunicação com o banco de dados MySQL via Docker

-Comunicação API externa via Spring Cloud OpenFeign  

-Validado  dados de entrada da API com Hibernate Validator

-Exceções  tratadas com ControllerAdvice e Problem Details (RFC 7807)

-Logs com o SLF4J

-TESTES UNITÁRIOS

Utilize o comando `docker compose up`  para  iniciar o container 


# transfer-api
Utilize o comando `cd \transfer-api`
Utilize o comando `docker build -t transfer-app .`  para criar sua imagem
 


# wallet-api
Utilize o comando `cd \wallet-api`
Utilize o comando `docker build -t wallet-app .`  para criar sua imagem


` - utilidades`
caminho para acessar o swagger  `http://localhost:9081/swagger-ui/index.html#/`



# Arquitetura

<img src="docs/images/architecture.png" alt="clean-architecture-diagram-1.png" width="700">
