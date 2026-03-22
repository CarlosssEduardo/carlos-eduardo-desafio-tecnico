> 📌 **Nota de Organização:** Renomeei o repositório e a pasta raiz para `carlos-eduardo-desafio-tecnico` pensando na comodidade de quem for avaliar. Como a maioria das entregas utiliza o nome padrão do desafio, essa alteração previne conflitos de diretórios ao clonar o projeto na sua máquina local!

# 💳 API de Gerenciamento de Pagamentos

Bem-vindo ao repositório da API de Pagamentos! Este projeto é uma API RESTful desenvolvida para gerenciar o recebimento e o processamento de pagamentos, simulando uma máquina de estados rigorosa e aplicando regras de exclusão lógica.

O foco principal deste desenvolvimento foi a entrega de um código limpo, padronizado, reutilizável e de fácil execução para avaliação.

## 🛠️ Tecnologias Utilizadas

* **Java 17 (LTS):** Linguagem principal do projeto.
* **Spring Boot:** Framework para criação rápida e autoconfigurável da API REST.
* **Spring Data JPA:** Abstração para persistência de dados.
* **H2 Database:** Banco de dados em memória (in-memory) para facilitar testes sem necessidade de infraestrutura externa.
* **Lombok:** Biblioteca para redução de código boilerplate (Getters, Setters, Construtores).
* **Docker:** Conteinerização da aplicação para execução simplificada.
* **Postman / Insomnia:** Ferramentas recomendadas para o teste das requisições.

---

## ⚙️ Máquina de Estados e Regras de Negócio

A API garante a integridade dos dados através das seguintes regras:
1.  Todo pagamento é registrado inicialmente com o status `PENDENTE_PROCESSAMENTO`.
2.  Um pagamento pendente pode transitar para `PROCESSADO_SUCESSO` ou `PROCESSADO_FALHA`.
3.  **Bloqueio de Sucesso:** Se o pagamento atingir `PROCESSADO_SUCESSO`, seu status não poderá mais ser alterado.
4.  **Ciclo de Falha:** Se o pagamento atingir `PROCESSADO_FALHA`, ele só poderá retroceder para `PENDENTE_PROCESSAMENTO`.
5.  **Exclusão Lógica:** Pagamentos só podem ser inativados (`ativo = false`) se estiverem com o status pendente.

---

## 🐳 Como Executar o Projeto (Via Docker)

Pensando na facilidade de avaliação, o projeto foi totalmente conteinerizado. **Não é necessário abrir a aplicação em uma IDE (como IntelliJ) para testar.** Basta ter o Docker instalado e rodando em sua máquina.

**Passo 1: Clone o repositório**
Abra o seu terminal e baixe o projeto:
```bash
git clone [https://github.com/SEU_USUARIO/carlos-eduardo-desafio.git](https://github.com/SEU_USUARIO/carlos-eduardo-desafio-tecnico.git)