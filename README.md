# 📚| Biblioteca - REST API

Essa é uma REST API desenvolvida para facilitar o gerenciamento de livros e empréstimos de uma biblioteca.

Aplicação feita para a disciplina de **_Linguagem de Programação_** da _UNINTER_.

## 🚀 Tecnologias:

Esse projeto foi desenvolvido usando as seguintes tecnologias: _Java 17_, _Spring Boot_, _Spring Doc_, _MySQL Workbench_, _Maven_, _IntelliJ IDEA_.

## 💻 Pré-requisitos:

- IDE (IntelliJ IDEA, Eclipse, etc)
- Java 17
- MySQL Workbench

## 📃 Funcionalidades:

Com essa aplicação é possivel:

- Cadastrar, atualizar, emprestar, excluir e buscar livros;
- Cadastrar, atualizar, excluir e buscar usuários;
- Cadastrar, atualizar, devolver, excluir e buscar empréstimos;

## 🛠️ Como configurar a aplicação:

1. Faça o download do arquivo zip do repositório.
2. Descompacte o arquivo zip.
3. Importe o projeto como um _Maven Project_ em uma IDE de sua escolha.
4. Em `src/main/resources/application.properties` configure as variáveis `spring.datasource.username` e `spring.datasource.password` de acordo com as informações do seu banco de dados.
5. Importe a collection do Postman, que é o arquivo `Biblioteca-API.postman_collection.json`.

## 👩‍💻 Como usar:

1. Execute a classe `BibliotecaApiAplication.java`.
2. Utilize o _Postman_ para usar as rotas da aplicação. **OU**
3. Acesse `http://localhost:8080/swagger-ui.html` para verificar as rotas da API.
