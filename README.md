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

## ⚙️ Regras de Negócio
API garante a integridade financeira através de transições validadas:
1. Todo pagamento inicia como `PENDENTE_PROCESSAMENTO`.
2. Pode transitar para `PROCESSADO_SUCESSO` ou `PROCESSADO_FALHA`.
3. **Trava de Segurança:** Um status `PROCESSADO_SUCESSO` é um estado final e **não permite** novas alterações, evitando fraudes ou erros de duplicidade.

---

## 🐳 Como Executar no Docker (Passo a Passo)
### Passo 0: Preparação
Abra o seu terminal (CMD ou PowerShell) e navegue até a pasta onde deseja salvar o projeto.
*Exemplo para salvar na Área de Trabalho:*
```bash
cd Desktop
````

### 1. Clonar e Acessar
```bash
git clone https://github.com/CarlosssEduardo/carlos-eduardo-desafio-tecnico.git

````

### 2\. Acessar a Pastar

```bash
cd carlos-eduardo-desafio-tecnico.
```
### 3\. Construir a Imagem (Build)

O Docker baixará as dependências e compilará o projeto automaticamente:

```bash
docker build -t api-pagamentos .
```

### 4\. Rodar a Aplicação

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

Mude o status para validar a regra de negócio
⚠️ Atenção: Caso você altere a porta no comando do Docker, lembre-se de atualizar a URL das requisições abaixo para a nova porta escolhida:
`PUT http://localhost:8081/pagamentos/{id}/status?novoStatus=PROCESSADO_SUCESSO`


### 3\. Como Listar TODOS os Pagamentos
Mude o status para validar a regra de negócio
⚠️ Atenção: Caso você altere a porta no comando do Docker, lembre-se de atualizar a URL das requisições abaixo para a nova porta escolhida:
`GET http://localhost:8081/pagamentos`

### 4. Como Fazer Buscas com Filtros (Query Params)
Para buscar pagamentos específicos (ex: só os que estão pendentes ou de um CPF específico), usamos os chamados **Query Parameters** (parâmetros de consulta). No Postman, isso é super visual:

1. Deixe o método como **`GET`** e a URL base como `http://localhost:8081/pagamentos`.
2. Logo abaixo da barra de URL, clique na aba **Params** (geralmente já vem selecionada por padrão).
3. Você verá uma tabelinha com "Key" (Chave) e "Value" (Valor). É aqui que você monta os seus filtros.

**Exemplo Prático 1: Filtrar por Status**
* Na coluna **Key**, digite: `status`
* Na coluna **Value**, digite: `PENDENTE_PROCESSAMENTO`
  *(Note que o Postman vai montar a URL automaticamente lá em cima para: `http://localhost:8081/pagamentos?status=PENDENTE_PROCESSAMENTO`)*. Clique em **Send**.

**Exemplo Prático 2: Filtrar por CPF/CNPJ**
* Na coluna **Key**, digite: `cpfCnpj`
* Na coluna **Value**, digite: `123.456.789-00`
* Clique em **Send**.

**Exemplo Prático 3: Combinar Filtros**
Você pode usar várias linhas da tabela "Params" ao mesmo tempo!
* Linha 1 -> Key: `metodoPagamento` | Value: `PIX`
* Linha 2 -> Key: `status` | Value: `PROCESSADO_SUCESSO`
  *(A URL ficará: `http://localhost:8081/pagamentos?metodoPagamento=PIX&status=PROCESSADO_SUCESSO`)*.


### 5\. Exclusão Lógica (DELETE)

Inative um pagamento pendente
⚠️ Atenção: Caso você altere a porta no comando do Docker, lembre-se de atualizar a URL das requisições abaixo para a nova porta escolhida:
`DELETE http://localhost:8081/pagamentos/{id}`



-----

