# AgendaWeb API Documentation

**⚠️ Aviso: Esta aplicação está em fase de desenvolvimento. Alterações podem ocorrer sem aviso prévio.**

AgendaWeb é uma aplicação web desenvolvida para gerenciar tarefas de forma eficiente. Esta documentação descreve as rotas disponíveis na API e suas funcionalidades.

---

## Índice

- [Visão Geral](#visão-geral)
- [Recursos Disponíveis](#recursos-disponíveis)
    - [Health](#health)
    - [LogUpdate](#logupdate)
    - [Task](#task)
    - [User](#user)
- [Como Rodar o Projeto](#como-rodar-o-projeto)
- [Configurações Importantes](#configurações-importantes)

---

## Visão Geral

AgendaWeb fornece uma interface RESTful para gerenciar tarefas, logs de atualizações e usuários. O sistema implementa boas práticas como validação de dados, versionamento de API e documentação interativa via Swagger.

---

## Recursos Disponíveis

### Health

**Endpoint:** `/api/health`  
**Descrição:** Fornece o status de saúde da aplicação, incluindo informações gerais, tempo de execução e status do banco de dados.

- **GET /api/health**  
  **Resposta:**
  ```json
  {
      "applicationName": "AgendaWeb",
      "version": "1.0.0",
      "status": "UP",
      "timestamp": "2025-01-22T12:34:56",
      "uptime": "123456 ms",
      "freeMemory": "256 MB",
      "totalMemory": "512 MB",
      "maxMemory": "1024 MB",
      "databaseStatus": "UP"
  }
  ```

---

### LogUpdate

**Endpoint Base:** `/api/logs`  
**Descrição:** Gerencia os logs de atualizações realizadas nas tarefas.

- **GET /api/logs**  
  Retorna todos os logs registrados.

- **GET /api/logs/task/{taskId}**  
  Retorna os logs relacionados a uma tarefa específica.

- **GET /api/logs/period?start={start}&end={end}**  
  Retorna os logs registrados em um período especificado.

- **GET /api/logs/user/{userId}**  
  Retorna os logs relacionados a um usuário específico.

---

### Task

**Endpoint Base:** `/api/tasks`  
**Descrição:** Gerencia as tarefas do sistema.

- **POST /api/tasks**  
  Cria uma nova tarefa.  
  **Payload:**
  ```json
  {
      "title": "Título da tarefa",
      "description": "Descrição detalhada",
      "scheduledDate": "2025-01-22",
      "startTime": "09:00",
      "endTime": "10:00",
      "userId": 1
  }
  ```

- **PUT /api/tasks/{id}**  
  Atualiza uma tarefa existente.

- **DELETE /api/tasks/{id}**  
  Remove uma tarefa.

- **GET /api/tasks**  
  Retorna todas as tarefas registradas.

- **GET /api/tasks/{id}**  
  Retorna os detalhes de uma tarefa específica.

---

### User

**Endpoint Base:** `/api/users`  
**Descrição:** Gerencia os usuários do sistema.

- **POST /api/users**  
  Cadastra um novo usuário.  
  **Payload:**
  ```json
  {
      "name": "Nome do usuário",
      "email": "email@exemplo.com",
      "password": "senha123"
  }
  ```

---

## Como Rodar o Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/agenda-web.git
   ```

2. Acesse o diretório do projeto:
   ```bash
   cd agenda-web
   ```

3. Configure as variáveis de ambiente no arquivo `application.properties`.

4. Execute o comando para iniciar a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Acesse a documentação interativa no navegador:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## Configurações Importantes


- **Porta Padrão:** A aplicação utiliza a porta `8080`.
- **Ferramentas:** O projeto foi desenvolvido utilizando Spring Boot, Hibernate e Swagger para documentação.

---

---

**Desenvolvido com 💻 por [Sergio Ajiki](https://github.com/sergioajiki).**

