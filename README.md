# Product Manager API

API REST em desenvolvimento para cadastro e gerenciamento de produtos, construída com Java e Spring Boot. O projeto organiza o processamento das requisições em camadas e usa Spring Data JPA para persistir a entidade `Produto` em MySQL.

## Objetivo

O objetivo é disponibilizar operações de backend para trabalhar com produtos. No estado atual, a API recebe e persiste um novo produto. A rota de listagem está criada, mas ainda retorna uma mensagem fixa em vez de consultar os registros.

## Funcionalidades implementadas

- Recebimento de um produto em JSON por meio de `POST /produtos`.
- Persistência do produto através do service e do repositório JPA.
- Geração do identificador do produto pelo banco, conforme o mapeamento da entidade.
- Operações de consulta por todos os produtos e por ID disponíveis na camada service, ainda sem rotas correspondentes funcionais no controller.

## Tecnologias

| Tecnologia | Uso no projeto |
| --- | --- |
| Java 21 | Linguagem e versão configurada para compilação |
| Spring Boot 4.1.1 | Inicialização e configuração da aplicação |
| Spring Web MVC | Endpoints HTTP |
| Spring Data JPA | Abstração de persistência e repositório |
| Hibernate | Implementação JPA usada pelo starter de Spring Data JPA |
| MySQL Connector/J | Conexão JDBC com MySQL |
| Bean Validation | Dependência incluída; não há regras de validação aplicadas ao model ou às requisições atualmente |
| Maven | Gerenciamento de dependências e build; Maven Wrapper incluído |

## Arquitetura

O código está separado por responsabilidade:

| Camada | Responsabilidade atual |
| --- | --- |
| `controller` | Mapeia as rotas de produtos e encaminha a criação ao service. |
| `service` | Implementa operações de persistência e consulta usando o repositório. |
| `repository` | Estende `JpaRepository<Produto, Integer>` para acesso aos dados. |
| `model` | Define `Produto` como entidade JPA mapeada para a tabela `produto`. |

O model contém `id`, `nome`, `preco` (`BigDecimal`) e `quantidade`. O identificador usa geração `IDENTITY`. Não há DTOs no projeto; o endpoint de criação recebe e retorna diretamente a entidade `Produto`.

## Endpoints atuais

| Método | Rota | Comportamento atual |
| --- | --- | --- |
| `POST` | `/produtos` | Persiste o produto recebido no corpo JSON e retorna a entidade salva. |
| `GET` | `/produtos` | Retorna o texto `Listando produtos`; não consulta o repositório. |

### Exemplo: criar produto

Corpo da requisição para `POST /produtos`:

```json
{
  "nome": "Teclado",
  "preco": 149.90,
  "quantidade": 10
}
```

Resposta representativa de `POST /produtos` (o valor de `id` é gerado pelo banco):

```json
{
  "id": 1,
  "nome": "Teclado",
  "preco": 149.90,
  "quantidade": 10
}
```

## Conceitos de backend praticados

- Separação de responsabilidades entre controller, service, repository e model.
- Mapeamento de entidade e identificador com Jakarta Persistence.
- Injeção de dependência por construtor.
- Persistência baseada em repositório com Spring Data JPA.
- Recebimento e resposta de dados JSON em endpoints HTTP.
- Configuração de credencial do banco por variável de ambiente.

## Estrutura do projeto

```text
.
├── .mvn/wrapper/
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

## Status e próximos passos

**Status:** projeto em desenvolvimento. O cadastro de produto está conectado à persistência; a rota `GET /produtos` ainda é provisória. Existe um teste de inicialização do contexto Spring.

Possíveis evoluções, ainda não implementadas:

- Conectar `GET /produtos` à consulta de produtos do service.
- Criar rotas para consulta de produto por ID, atualização e remoção.
- Aplicar regras de Bean Validation às entradas.
- Adicionar testes para as operações e os endpoints.

## Como executar

Requer JDK 21 e MySQL. A configuração atual usa o banco `product_manager` em `127.0.0.1:3306`, com usuário `root`; a senha deve estar definida na variável de ambiente `DB_SENHA`. Com o banco disponível e a variável configurada, inicie pelo Maven Wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```