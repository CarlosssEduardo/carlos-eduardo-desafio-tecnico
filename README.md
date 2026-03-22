# 💳 API de Gerenciamento de Pagamentos (Desafio Técnico)

Esta é uma API RESTful desenvolvida em **Java 17** com **Spring Boot**, focada no processamento de pagamentos, controle de estados e persistência de dados com **H2 Database**.

> 📌 **Diferencial de Organização:** O projeto utiliza **Docker Multi-stage Build**. Não precisa configurar Java ou Gradle localmente; o Docker compila e roda a aplicação inteiramente isolada.

---

## 🛠️ Tecnologias e Padrões
* **Java 17 & Spring Boot 3**
* **Spring Data JPA** (Persistência)
* **H2 Database** (Banco em memória)
* **Lombok** (Produtividade)
* **Docker** (Conteinerização profissional)
* **Exclusão Lógica (Soft Delete):** Registros inativados permanecem no banco.

---

## ⚙️ Máquina de Estados (Regras de Negócio)
API garante a integridade financeira através de transições validadas:
1. Todo pagamento inicia como `PENDENTE_PROCESSAMENTO`.
2. Pode transitar para `PROCESSADO_SUCESSO` ou `PROCESSADO_FALHA`.
3. **Trava de Segurança:** Um status `PROCESSADO_SUCESSO` é um estado final e **não permite** novas alterações, evitando fraudes ou erros de duplicidade.

---

## 🐳 Como Executar Docker (Passo a Passo)

### 1. Clonar e Acessar
```bash
git clone [https://github.com/CarlosssEduardo/carlos-eduardo-desafio-tecnico.git](https://github.com/CarlosssEduardo/carlos-eduardo-desafio-tecnico.git)
cd carlos-eduardo-desafio-tecnico
````

### 2\. Construir a Imagem (Build)

O Docker baixará as dependências e compilará o projeto automaticamente:

```bash
docker build -t api-pagamentos .
```

### 3\. Rodar a Aplicação

Para rodar na porta padrão (`8081`), utilize:

```bash
docker run -p 8081:8081 api-pagamentos
```

> 💡 **Dica de Infraestrutura:** Caso a porta `8081` já esteja ocupada em sua máquina, você pode mapear para qualquer outra porta local (Ex: `8090`) alterando o comando para:
> `docker run -p 8090:8081 api-pagamentos`
> *(Nesse caso, a API responderá em http://localhost:8090)*

-----

## 🧪 Guia de Testes (Postman/Insomnia)

### 1\. Criar Pagamento (POST)

**URL:** `http://localhost:8081/pagamentos` (ou a porta escolhida no passo anterior)

**Exemplo PIX:**

```json
{
  "codigoDebito": 101,
  "cpfCnpj": "123.456.789-00",
  "metodoPagamento": "PIX",
  "valor": 50.00
}
```

**Exemplo Cartão de Crédito:**

```json
{
  "codigoDebito": 102,
  "cpfCnpj": "123.456.789-00",
  "metodoPagamento": "CARTAO_CREDITO",
  "numeroCartao": "4444555566667777",
  "valor": 250.00
}
```

**Exemplo Boleto:**

```json
{
  "codigoDebito": 103,
  "cpfCnpj": "00.123.456/0001-00",
  "metodoPagamento": "BOLETO",
  "valor": 150.00
}
```

### 2\. Alterar Status (PUT)

Mude o status para validar a regra de negócio: (Verifica porta escolhida)
`PUT http://localhost:8081/pagamentos/{id}/status?novoStatus=PROCESSADO_SUCESSO`

### 3\. Exclusão Lógica (DELETE)

Inative um pagamento pendente: (Verifica porta escolhida)
`DELETE http://localhost:8081/pagamentos/{id}`

-----
