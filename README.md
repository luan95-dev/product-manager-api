# Product Manager API

API REST para cadastro de produtos, desenvolvida com Java e Spring Boot. Atualmente, o endpoint de criação persiste produtos no banco de dados; o endpoint de listagem ainda retorna uma mensagem fixa.

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA e Hibernate
- MySQL Connector/J
- Bean Validation (dependência configurada; regras de validação ainda não identificadas no modelo ou no controller)
- Maven, com Maven Wrapper incluído

## Organização

- **Controller:** recebe as requisições HTTP em `ProdutoController` e encaminha a criação ao service.
- **Service:** concentra operações de produtos em `ProdutoService`, incluindo persistência e métodos de consulta disponíveis.
- **Repository:** `ProdutoRepository` estende `JpaRepository` para acesso a dados com JPA.
- **Model/Entity:** `Produto` representa a entidade persistida na tabela `produto`, com os campos `id`, `nome`, `preco` e `quantidade`.
- **DTO:** não há DTO identificado; os endpoints usam diretamente o model `Produto`.

## Pré-requisitos

- JDK 21
- MySQL acessível localmente

## Banco de dados e variável de ambiente

A configuração atual conecta ao MySQL em `127.0.0.1:3306`, usando o banco `product_manager` e o usuário `root`. A senha é lida da variável de ambiente `DB_SENHA`; não armazene uma senha no README ou no controle de versão.

Crie o banco configurado, caso ainda não exista:

```sql
CREATE DATABASE product_manager;
```

No PowerShell, defina a variável para a sessão atual do terminal, substituindo o valor pelo segredo local:

```powershell
$env:DB_SENHA = "sua-senha-local"
```

## Executar localmente

Com o MySQL em execução, o banco criado e `DB_SENHA` definida no terminal:

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação usa a porta padrão do Spring Boot (`8080`).

## Endpoints disponíveis

### `POST /produtos`

Cadastra um produto usando o corpo JSON abaixo. O identificador é gerado pelo banco.

Requisição:

```json
{
  "nome": "Teclado",
  "preco": 149.90,
  "quantidade": 10
}
```

Resposta representativa, com o `id` gerado na persistência:

```json
{
  "id": 1,
  "nome": "Teclado",
  "preco": 149.90,
  "quantidade": 10
}
```

### `GET /produtos`

Retorna atualmente o texto fixo `Listando produtos`. Embora o service tenha um método para buscar produtos, o controller ainda não o utiliza neste endpoint.

## Estrutura de pastas

```text
.
├── .mvn/wrapper/                 # Configuração do Maven Wrapper
├── src/
│   ├── main/
│   │   ├── java/com/luan/product_manager_api/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── ProductManagerApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/com/luan/product_manager_api/
└── pom.xml
```