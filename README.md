# Sistema de Gestão de Projetos e Tarefas

Este projeto é uma **API RESTful** desenvolvida em **Java 17+** com **Spring Boot 3+**, que permite gerenciar projetos e suas tarefas (demandas) de forma simples. Ele é voltado para times de desenvolvimento acompanharem status, prioridades e datas de entrega de tarefas.

OBS: Projeto revisado, analisado e comentado em vídeo. 
- Youtube: https://youtu.be/PQNc3m0aDnw?si=cO1kxGiiij0QP_KH 
- Github: https://github.com/matheuslf
- Linkedin: https://www.linkedin.com/in/matheus-leandro-ferreira/

---

## 🏗 Tecnologias utilizadas

- Java 17+
- Spring Boot 3+
- Spring Data JPA
- PostgreSQL (ou H2 em memória para testes (no caso somente o H2))
- Bean Validation
- JUnit 5 + Mockito (Testes unitários)
- OpenAPI / Swagger (opcional como diferencial)
- Maven

---

## 📦 Estrutura do Projeto

- `domain` → entidades e enums (`Project`, `Task`, `Status`, `Priority`)
- `repository` → interfaces Spring Data JPA
- `service` → lógica de negócios
- `controller` → endpoints REST
- `dto` → Data Transfer Objects (Request/Response)
- `exception` → tratamento de erros com `@ControllerAdvice`
- `test` → testes unitários e de integração

---

## 🔧 Requisitos

- Java 17+
- Maven 3+
- PostgreSQL (ou H2 para desenvolvimento rápido)

---

## 🚀 Rodando o projeto localmente

1. Clone o repositório:

```bash
git clone https://github.com/RafaSamm/DesafioJunior-Outubro2025.git
cd DesafioJunior-Outubro2025

```
2. Execute o projeto com maven:

```bash
mvn clean install
mvn spring-boot:run
```

## ⬇️Endpoints Principais

| Método | Endpoint                                                | Descrição                                                          |
| ------ | --------------------                                    | ------------------------------------------------------------------ |
| POST   | `/projects`                                             | Criar projeto                                                      |
| GET    | `/projects`                                             | Listar projetos (com paginação opcional)                           |
| POST   | `/tasks`                                                | Criar tarefa vinculada a um projeto                                |
| GET    | `tasks?projectId=?&status=?&priority=?&page=?&size=?`  | Listar tarefas com filtros opcionais (status, prioridade, projeto) |
| PUT    | `/tasks/{id}/status`                                    | Atualizar status da tarefa                                         |
| DELETE | `/tasks/{id}`                                           | Deletar tarefa                                                     |



## 🧪 Testes

```bash
mvn test
```
Unitários: Services com repositórios mockados



