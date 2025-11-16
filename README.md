# 🗂️ TaskFlow API – Sistema de Gerenciamento de Usuários e Tarefas 
- API REST construída com Spring Boot para gerenciamento de usuários e tarefas.
- Inclui criação, atualização parcial, filtros avançados, paginação e documentação automática via Swagger/OpenAPI.

## Tecnologias usadas 
- ☕ Java 
- 🌱 Spring Boot 
- 🗃️ JPA / Hibernate 
- 🍃 Maven 
- 🐬 MySQL 
- 📝 Swagger / OpenAPI 

## 🔧 Funcionalidades 
- CRUD completo de Usuários e Tasks
- Atualização parcial (PATCH)
- Paginação nativa com Pageable
- Filtros avançados: status, prioridade e usuário
- Relacionamento User ↔ Task (One-to-Many)
- Retorno com DTOs para proteger dados sensíveis
- Datas automáticas: createdAt / updatedAt
- Swagger/OpenAPI para documentação
- Tratamento global de erros (GlobalExceptionHandler)

## Estrutura das rotas

### 👤 Users
- POST /users
- GET /users/{id}
- GET /users
- GET /users/paginated
- PATCH /users/{id}
- PUT /users/{id}
- DELETE /users/{id}
- POST /users/{id}/tasks ← cria task para user
### 🗂️ Tasks
- POST /tasks
- GET /tasks/{id}
- GET /tasks
- GET /tasks/paginated
- GET /tasks/filtered
- PATCH /tasks/{id}
- PUT /tasks/{id}
- DELETE /tasks/{id}

## ▶️ Como executar 
1. Clone o repositório
2. Crie um schema MySQL chamado: **taskflowdb**
3. Renomeie o arquivo **application-example.properties** para **application.properties** e preencha com os dados da sua conexão MySQL:
   - spring.datasource.url=jdbc:mysql://localhost:3306/taskflowdb
   - spring.datasource.username=**USERNAME**
   - spring.datasource.password=**PASSWORD**
4. Execute a aplicação: src/main/java/**TaskflowApplication**

## 📘 Documentação da API (Swagger / OpenAPI)
- Ao rodar o projeto, acesse o Link: **http://localhost:8080/swagger-ui/index.html**
- Lá você encontra:
  - Exemplos
  - Schemas
  - Endpoints para testar a aplicação.
