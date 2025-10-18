# Sistema de Gestão de Projetos e Tarefas

Este projeto é uma **API RESTful** desenvolvida em **Java 17+** com **Spring Boot 3+**, que permite gerenciar projetos e suas tarefas (demandas) de forma simples. Ele é voltado para times de desenvolvimento acompanharem status, prioridades e datas de entrega de tarefas.

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
cd sistema-gestao-projetos
