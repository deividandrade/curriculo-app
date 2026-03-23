# 📄 CurriculoApp

Aplicação web para **cadastro de currículos**, desenvolvida com Java Spring Boot, PostgreSQL, Thymeleaf, HTML, CSS e JavaScript.

---

![curriculo-app](https://github.com/user-attachments/assets/f43a11b5-0e17-4ce0-9b83-34078d2d2dbf)

---

## 🖥️ Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.2.3 | Framework backend |
| PostgreSQL | 18+ | Banco de dados relacional |
| Thymeleaf | - | Template engine (HTML server-side) |
| Lombok | - | Redução de boilerplate Java |
| Maven | - | Gerenciamento de dependências |
| HTML / CSS / JS | - | Interface do usuário |

---

## 📦 Dependências Spring Boot

```xml
<!-- Servidor web + MVC + REST -->
spring-boot-starter-web

<!-- ORM com Hibernate para persistência -->
spring-boot-starter-data-jpa

<!-- Template engine para renderizar HTML -->
spring-boot-starter-thymeleaf

<!-- Validação de campos (@NotBlank, @Email...) -->
spring-boot-starter-validation

<!-- Driver JDBC para PostgreSQL -->
postgresql

<!-- Reduz boilerplate com @Data, @Builder... -->
lombok

<!-- Hot reload em desenvolvimento -->
spring-boot-devtools

<!-- Testes com JUnit 5 + Mockito -->
spring-boot-starter-test
```

---


## ⚙️ Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- [Java 17+](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/)
- [PostgreSQL 13+](https://www.postgresql.org/download/)
- IDE de sua preferência (Eclipse, IntelliJ, VS Code)

---

## 🚀 Como Rodar o Projeto

### 1. Clone ou extraia o projeto

```bash
# Se via Git
git clone https://github.com/seu-usuario/curriculo-app.git
cd curriculo-app

# Ou extraia o ZIP baixado
```

### 2. Crie o banco de dados no PostgreSQL

Abra o pgAdmin ou o terminal e execute:

```sql
CREATE DATABASE curriculo_db;
```

### 3. Configure as credenciais

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/curriculo_db
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI
```

### 4. Execute a aplicação

**Via terminal:**
```bash
mvn spring-boot:run
```

**Via Eclipse:**
1. Importe como `Maven → Existing Maven Projects`
2. Clique com botão direito em `CurriculoAppApplication.java`
3. Selecione `Run As → Java Application` (ou `Spring Boot App` se tiver o plugin STS)

### 5. Acesse no navegador

```
http://localhost:8080/curriculo/cadastro
```

---

## 🌐 Rotas da Aplicação

| Método | Rota | Descrição |
|---|---|---|
| GET | `/curriculo/cadastro` | Exibe o formulário de cadastro |
| POST | `/curriculo/cadastro` | Processa e salva o currículo |
| GET | `/curriculo/sucesso` | Página de confirmação de cadastro |
| GET | `/curriculo/lista` | Lista todos os currículos cadastrados |

---

## 📋 Funcionalidades

- ✅ Formulário dividido em **4 etapas** com navegação lateral
- ✅ Cadastro de **dados pessoais** (nome, e-mail, telefone, data de nascimento, cidade, estado, LinkedIn, GitHub)
- ✅ Cadastro de **formação acadêmica** (múltiplas entradas dinâmicas)
- ✅ Cadastro de **experiência profissional** (múltiplas entradas dinâmicas)
- ✅ Cadastro de **habilidades técnicas** e **idiomas** com preview de tags
- ✅ **Validação** de campos obrigatórios no servidor
- ✅ **Máscara** de telefone no frontend
- ✅ **Contador** de caracteres no campo objetivo
- ✅ Listagem de todos os currículos cadastrados
- ✅ Tabelas criadas **automaticamente** pelo Hibernate

---

## 🔧 Configurações do application.properties

```properties
# Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/curriculo_db
spring.datasource.username=postgres
spring.datasource.password=sua_senha

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update   # Cria/atualiza tabelas automaticamente
spring.jpa.show-sql=true               # Exibe SQLs no console

# Servidor
server.port=8080

# Upload de arquivos
spring.servlet.multipart.max-file-size=5MB
```

---

## Observações

**Instalar Lombok no Eclipse:**
1. Baixe em https://projectlombok.org/download
2. Execute: `java -jar lombok.jar`
3. O instalador detecta o Eclipse automaticamente
4. Reinicie o Eclipse e faça `Maven → Update Project`

**Instalar Spring Tools 4 no Eclipse:**
1. `Help → Eclipse Marketplace`
2. Busque por `Spring Tools 4`
3. Instale e reinicie

---

# 👨‍💻 Autor

Desenvolvido por **Deivid Andrade**
