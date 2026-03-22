# 💳 API de Gerenciamento de Pagamentos - Desafio Técnico

Bem-vindo ao repositório da API de Pagamentos - De Carlos Eduardo - Desenvolvedor Java! Este projeto é uma API RESTful desenvolvida para gerenciar o recebimento e o processamento de pagamentos, simulando uma máquina de estados rigorosa e aplicando regras de exclusão lógica.

> 📌 **Nota de Organização:** O repositório e a pasta raiz foram nomeados como `carlos-eduardo-desafio-tecnico` pensando na comodidade da equipe de avaliação, prevenindo conflitos de diretórios (como pastas duplicadas) ao clonar o projeto na máquina local.

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído focando em simplicidade, código limpo e facilidade de execução:
* **Java 17 (LTS)**
* **Spring Boot** (Web, Data JPA)
* **H2 Database** (Banco de dados em memória para facilitar os testes)
* **Lombok** (Redução de código boilerplate)
* **Docker** (Conteinerização da aplicação)
* **Postman / Insomnia** (Para validação dos endpoints)

---

## ⚙️ Máquina de Estados e Regras de Negócio

API garante a integridade dos dados através das seguintes regras:
1. Todo pagamento nasce obrigatoriamente com o status `PENDENTE_PROCESSAMENTO`.
2. Um pagamento pendente pode transitar para `PROCESSADO_SUCESSO` ou `PROCESSADO_FALHA`.
3. **Bloqueio de Sucesso:** Se o pagamento atingir `PROCESSADO_SUCESSO`, seu status não poderá mais ser alterado.
4. **Ciclo de Falha:** Se o pagamento atingir `PROCESSADO_FALHA`, ele só poderá retroceder para `PENDENTE_PROCESSAMENTO`.
5. **Exclusão Lógica:** Pagamentos só podem ser inativados (`ativo = false`) se estiverem com o status pendente.

---

## 🐳 Como Executar o Projeto (Via Docker)

O projeto foi totalmente conteinerizado. **Não é necessário abrir a aplicação em uma IDE para testar.** Basta ter o Docker instalado e rodando.

**Passo 1: Clone o repositório**
Abra o seu terminal e baixe o projeto:
```bash
git clone [https://github.com/CarlosssEduardo/carlos-eduardo-desafio-tecnico.git](https://github.com/CarlosssEduardo/carlos-eduardo-desafio-tecnico.git)