# 🛒 Vendas API – Java + PostgreSQL + Docker + PgAdmin

Este projeto é um exemplo de API simples utilizando **Java + JDBC**, banco de dados **PostgreSQL** rodando em **Docker**, com interface de administração pelo **PgAdmin**.

---

## 🚀 Tecnologias

- Java 17
- PostgreSQL
- Docker & Docker Compose
- PgAdmin
- JDBC (Conexão direta sem frameworks)

---

## 📂 Estrutura do Projeto

```
projeto_3/
├─ src/
│  ├─ app/
│  │  └─ Main.java
│  ├─ dao/
│  │  └─ ClienteDAO.java, ProdutoDAO.java, VendaDAO.java
│  ├─ model/
│  │  └─ Cliente.java, Produto.java, Venda.java
│  └─ database/
│     └─ Database.java
├─ lib/
│  └─ postgresql-42.7.1.jar
├─ docker-compose.yml
├─ .gitignore
├─ README.md
└─ bin/ (gerado na compilação)
```

---

## ⚙️ Pré-Requisitos

- Docker e Docker Compose
- Java 17 ou superior
- VS Code ou outro editor Java
- Git

---

## 📌 Como executar o projeto

### 1️⃣ Subir banco de dados e PgAdmin

Na raiz do projeto:

```bash
docker-compose up -d
```

Acesse o PgAdmin:

- http://localhost:5050  
- Email: **admin@admin.com**  
- Senha: **admin**

No PgAdmin, adicione um servidor:

| Campo        | Valor         |
|------------|--------------|
| Host      | postgres     |
| Port      | 5432         |
| Username  | postgres     |
| Password  | postgres     |
| Database  | vendasdb     |

### 2️⃣ Criar as tabelas

No Query Tool do PgAdmin, execute:

```sql
CREATE TABLE cliente (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100),
  email VARCHAR(100)
);

CREATE TABLE produto (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100),
  preco DECIMAL(10,2)
);

CREATE TABLE venda (
  id SERIAL PRIMARY KEY,
  cliente_id INT REFERENCES cliente(id),
  produto_id INT REFERENCES produto(id),
  quantidade INT
);
```

---

### 3️⃣ Compilar o projeto

No terminal:

```bash
mkdir bin
javac -cp "lib/*" -d bin src/**/*.java
```

---

### 4️⃣ Executar

**Windows:**
```bash
java -cp "lib/*;bin" app.Main
```

**Linux/Mac:**
```bash
java -cp "lib/*:bin" app.Main
```

---

### ✅ O que será feito

Quando rodar o projeto:

- Será cadastrado um cliente, um produto e uma venda.
- Os registros serão listados no console.
- Os dados ficarão salvos no PostgreSQL (você pode consultar pelo PgAdmin).

---

## 🚀 Próximos Passos

Se quiser evoluir o projeto:
- Implementar CRUD completo via API (Spring Boot)
- Criar testes automatizados com JUnit
- Adicionar autenticação (JWT)
