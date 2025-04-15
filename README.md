# 🛠️ Projeto Controle de Inventário Spring Boot

<div align="center">
  <strong>API RESTful robusta para o gerenciamento de inventário, movimentações de produtos, fornecedores, categorias e autenticação com segurança e performance!</strong>
</div>

<br />

<div align="center">
  <img src="https://img.shields.io/badge/SpringBoot-3.4.4-brightgreen?style=for-the-badge&logo=spring"/>
  <img src="https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java"/>
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql"/>
  <img src="https://img.shields.io/badge/JPA-Hibernate-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Swagger-Documentation-yellow?style=for-the-badge&logo=swagger"/>
</div>

---

## 📖 Índice

- [🔍 Visão Geral](#-visão-geral)
- [📦 Funcionalidades](#-funcionalidades)
- [✨ Diferenciais](#-diferenciais)
- [💻 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [⚙️ Configuração do Ambiente](#-configuração-do-ambiente)
- [🧪 Testes e Popular Dados](#-testes-e-popular-dados)
- [🤝 Como Contribuir](#-como-contribuir)
- [📄 Licença](#-licença)

---

## 🔍 Visão Geral

Este projeto é uma **API completa de controle de inventário** desenvolvida com **Spring Boot**, com foco em boas práticas, escalabilidade, modularização e clareza de código.

A API realiza o gerenciamento completo do **inventário de produtos**, incluindo:
- Cadastro de produtos, fornecedores, categorias e movimentações;
- Relacionamentos entre entidades como `@ManyToOne`, `@OneToMany`, `@ManyToMany`;
- Registro e visualização de movimentações de estoque (entrada, saída, ajustes);
- Cálculos automáticos de subtotal e total dos produtos movimentados.

---

## 📦 Funcionalidades

✔️ **Cadastro de Produtos** com informações como nome, quantidade em estoque e valor

✔️ **Cadastro de Fornecedores** e suas informações de contato (nome, email, telefone, CNPJ)

✔️ **Cadastro de Categorias** para organizar os produtos

✔️ **Movimentações de Estoque** com tipos como entrada e saída de produtos

✔️ **Relatórios de Movimentações** por produto ou fornecedor

✔️ **Cálculo automático do subtotal e total** das movimentações

✔️ **Autenticação de Usuários** com diferentes tipos de cargos (Operador, Caixa, PPCP, ADM)

✔️ **Controle de Status das Movimentações** como `ENTRADA` ou `SAÍDA`

✔️ **Requisições** `POST`, `GET`, `DELETE`, `PUT`, `PATCH` para interagir com a API

--

## 📈 Diferenciais

⚙️ **Uso de JPA com Hibernate** para mapeamento automático das entidades
🔁 **Relacionamentos complexos tratados com elegância** usando `@OneToMany`, `@ManyToOne`, `@ManyToMany`
🧠 **Cálculo de subtotal e total** dentro das entidades com `getSubTotal()` e `getTotal()`
📐 **Estrutura de Resources e Services bem definidas** para fácil manutenção
🎯 **Respostas completas em JSON** com todos os relacionamentos necessários para integração com frontend ou testes via Postman

---

## 💻 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** 3.4.4
- **Spring Data JPA**
- **PostgreSQL** (ou H2 para ambiente de testes)
- **Hibernate**
- **Maven**

---

## ⚙️ Configuração do Ambiente

### Pré-requisitos

- **JDK 17** instalado
- **IDE** (IntelliJ, Eclipse, STS ou VS Code com suporte a Java)
- **PostgreSQL** (ou use o banco em memória H2 para testes)
- **Maven**

### Passos

1. **Clone o projeto**:
   ```bash
   git clone https://github.com/joaosehn2406/ecommerce-spring.git
   cd seu-projeto-ecommerce
   ```

2. **Configure o banco de dados** no `application.properties` com as credenciais do PostgreSQL (ou use o `application-test.properties` para o banco H2).

3. **Execute a aplicação**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Acesse os endpoints** via Postman ou front-end (porta padrão: `http://localhost:8080`).

---

## 🧪 Testes e Popular Dados

O projeto vem com uma classe **`TestConfig`** com o **profile `test`** que popula automaticamente:
- **Usuários** (`User`)
- **Produtos** (`Product`)
- **Categorias** (`Category`)
- **Movimentações** (`Movimentacao`)

Ideal para testes e aprendizado sobre o funcionamento da API.

---

## 🤝 Como Contribuir

1. Faça um **fork** do projeto.
2. Crie uma **branch** com a sua feature: `git checkout -b minha-feature`.
3. **Commit** suas mudanças: `git commit -m 'feat: minha nova feature'`.
4. **Push** para sua branch: `git push origin minha-feature`.
5. Abra um **Pull Request**.

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Consulte [MIT License](https://mit-license.org/) para mais detalhes.

---

Desenvolvido com foco em **boas práticas**, **didática** e **organização**. Ideal para aprender e evoluir com **Spring Boot** e **JPA**!

