package com.uninter.biblioteca.swagger;


// classe que contém os exemplos de respostas da aplicação
// tais exemplos são usados na documentação do swagger
public class ExemplosResponses {

   public static final String criarEmprestimoResponse ="{\n" +
            "    \"id\": 10,\n" +
            "    \"usuario\": {\n" +
            "        \"id\": 1,\n" +
            "        \"nome\": \"ana\",\n" +
            "        \"email\": \"ana@4106985.com\",\n" +
            "        \"senha\": \"$2a$10$LNmwV8EEPTPFW1irTO0lP.bd.2O8gHlz06kvqREWazlPOcffA1jsu\",\n" +
            "        \"cargo\": \"LEITOR\"\n" +
            "    },\n" +
            "    \"livro\": {\n" +
            "        \"id\": 1,\n" +
            "        \"titulo\": \"titulo\",\n" +
            "        \"autor\": \"autor\",\n" +
            "        \"isbn\": \"123-4-56-722101-3\",\n" +
            "        \"genero\": \"genero\",\n" +
            "        \"disponibilidade\": \"INDISPONIVEL\"\n" +
            "    },\n" +
            "    \"data_emprestimo\": \"28-06-2024\",\n" +
            "    \"data_devolucao\": \"01-01-2025\",\n" +
            "    \"status\": \"PENDENTE\"\n" +
            "}";

   public static final String atualizarEmprestimoResponse="{\n" +
           "    \"id\": 10,\n" +
           "    \"usuario\": {\n" +
           "        \"id\": 4,\n" +
           "        \"nome\": \"usuario\",\n" +
           "        \"email\": \"usuario@4106985.com\",\n" +
           "        \"senha\": \"$2a$10$LNmwV8EEPTPFW1ir00Af.bd.2O8gHlz06kvqREWazlPOcffA1jsu\",\n" +
           "        \"cargo\": \"LEITOR\"\n" +
           "    },\n" +
           "    \"livro\": {\n" +
           "        \"id\": 1,\n" +
           "        \"titulo\": \"titulo\",\n" +
           "        \"autor\": \"autor\",\n" +
           "        \"isbn\": \"123-4-56-722101-3\",\n" +
           "        \"genero\": \"genero\",\n" +
           "        \"disponibilidade\": \"INDISPONIVEL\"\n" +
           "    },\n" +
           "    \"data_emprestimo\": \"28-06-2024\",\n" +
           "    \"data_devolucao\": \"02-03-2025\",\n" +
           "    \"status\": \"PENDENTE\"\n" +
           "}";

   public static final String devolverEmprestimoResponse= "{\n" +
           "    \"id\": 2,\n" +
           "    \"usuario\": {\n" +
           "        \"id\": 1,\n" +
           "        \"nome\": \"ana\",\n" +
           "        \"email\": \"ana\",\n" +
           "        \"senha\": \"$2a$10$LNmwV8EEPTPFW1irTO0lP.bd.2O8gHlz06kvqREWazlPOcffA1jsu\",\n" +
           "        \"cargo\": \"BIBLIOTECARIO\"\n" +
           "    },\n" +
           "    \"livro\": {\n" +
           "        \"id\": 2,\n" +
           "        \"titulo\": \"titulo\",\n" +
           "        \"autor\": \"autor\",\n" +
           "        \"isbn\": \"947-8-16-148415-8\",\n" +
           "        \"genero\": \"fantasia\",\n" +
           "        \"disponibilidade\": \"DISPONIVEL\"\n" +
           "    },\n" +
           "    \"data_emprestimo\": \"2024-06-26\",\n" +
           "    \"data_devolucao\": \"28-06-2024\",\n" +
           "    \"status\": \"DEVOLVIDO\"\n" +
           "}";

   public static final String obterEmprestimoPorIdResponse= "{\n" +
           "    \"id\": 3,\n" +
           "    \"usuario\": {\n" +
           "        \"id\": 1,\n" +
           "        \"nome\": \"ana\",\n" +
           "        \"email\": \"ana\",\n" +
           "        \"senha\": \"$2a$10$LNmwV8EEPTPFW1irTO0lP.bd.2O8gHlz06kvqREWazlPOcffA1jsu\",\n" +
           "        \"cargo\": \"BIBLIOTECARIO\"\n" +
           "    },\n" +
           "    \"livro\": {\n" +
           "        \"id\": 3,\n" +
           "        \"titulo\": \"titulo\",\n" +
           "        \"autor\": \"autor\",\n" +
           "        \"isbn\": \"947-8-16-148285-8\",\n" +
           "        \"genero\": \"fantasia\",\n" +
           "        \"disponibilidade\": \"INDISPONIVEL\"\n" +
           "    },\n" +
           "    \"data_emprestimo\": \"2024-06-26\",\n" +
           "    \"data_devolucao\": null,\n" +
           "    \"status\": \"PENDENTE\"\n" +
           "}";

   public static final String obterTodosEmprestimosResponse= "[\n" +
           "    {\n" +
           "        \"id\": 9,\n" +
           "        \"usuario\": {\n" +
           "            \"id\": 1,\n" +
           "            \"nome\": \"ana\",\n" +
           "            \"email\": \"ana\",\n" +
           "            \"senha\": \"$2a$10$LNmwV8EEPTPFW1irTO0lP.bd.2O8gHlz06kvqREWazlPOcffA1jsu\",\n" +
           "            \"cargo\": \"BIBLIOTECARIO\"\n" +
           "        },\n" +
           "        \"livro\": {\n" +
           "            \"id\": 10,\n" +
           "            \"titulo\": \"titulo\",\n" +
           "            \"autor\": \"autor\",\n" +
           "            \"isbn\": \"123-4-56-789101-8\",\n" +
           "            \"genero\": \"fantasia\",\n" +
           "            \"disponibilidade\": \"INDISPONIVEL\"\n" +
           "        },\n" +
           "        \"data_emprestimo\": \"2024-06-28\",\n" +
           "        \"data_devolucao\": \"2002-01-01\",\n" +
           "        \"status\": \"PENDENTE\"\n" +
           "    },\n" +
           "    {\n" +
           "        \"id\": 10,\n" +
           "        \"usuario\": {\n" +
           "            \"id\": 1,\n" +
           "            \"nome\": \"ana\",\n" +
           "            \"email\": \"ana\",\n" +
           "            \"senha\": \"$2a$10$LNmwV8EEPTPFW1irTO0lP.bd.2O8gHlz06kvqREWazlPOcffA1jsu\",\n" +
           "            \"cargo\": \"BIBLIOTECARIO\"\n" +
           "        },\n" +
           "        \"livro\": {\n" +
           "            \"id\": 12,\n" +
           "            \"titulo\": \"titulo\",\n" +
           "            \"autor\": \"autor\",\n" +
           "            \"isbn\": \"123-4-56-722101-3\",\n" +
           "            \"genero\": \"fantasia\",\n" +
           "            \"disponibilidade\": \"INDISPONIVEL\"\n" +
           "        },\n" +
           "        \"data_emprestimo\": \"2024-06-28\",\n" +
           "        \"data_devolucao\": \"2025-01-01\",\n" +
           "        \"status\": \"PENDENTE\"\n" +
           "    }\n" +
           "]";

   public static final String criarLivroResponse= "{\n" +
           "    \"id\": 13,\n" +
           "    \"titulo\": \"titulo\",\n" +
           "    \"autor\": \"autor\",\n" +
           "    \"isbn\": \"123-4-56-789101-3\",\n" +
           "    \"genero\": \"genero\",\n" +
           "    \"disponibilidade\": \"DISPONIVEL\"\n" +
           "}";

   public static final String atualizarLivroResponse= "{\n" +
           "    \"id\": 13,\n" +
           "    \"titulo\": \"titulo atualizado\",\n" +
           "    \"autor\": \"autor\",\n" +
           "    \"isbn\": \"123-4-56-789101-3\",\n" +
           "    \"genero\": \"genero\",\n" +
           "    \"disponibilidade\": \"DISPONIVEL\"\n" +
           "}";

   public static final String obterLivroPorIdResponse= "{\n" +
           "    \"id\": 12,\n" +
           "    \"titulo\": \"titulo\",\n" +
           "    \"autor\": \"autor\",\n" +
           "    \"isbn\": \"123-4-56-722101-3\",\n" +
           "    \"genero\": \"fantasia\",\n" +
           "    \"disponibilidade\": \"INDISPONIVEL\"\n" +
           "}";

   public static final String obterTodosLivrosResponse= "[\n" +
           "    {\n" +
           "        \"id\": 10,\n" +
           "        \"titulo\": \"titulo\",\n" +
           "        \"autor\": \"autor\",\n" +
           "        \"isbn\": \"123-4-56-789101-8\",\n" +
           "        \"genero\": \"fantasia\",\n" +
           "        \"disponibilidade\": \"INDISPONIVEL\"\n" +
           "    },\n" +
           "    {\n" +
           "        \"id\": 11,\n" +
           "        \"titulo\": \"titulo\",\n" +
           "        \"autor\": \"autor\",\n" +
           "        \"isbn\": \"123-4-56-789101-2\",\n" +
           "        \"genero\": \"fantasia\",\n" +
           "        \"disponibilidade\": \"INDISPONIVEL\"\n" +
           "    },\n" +
           "    {\n" +
           "        \"id\": 12,\n" +
           "        \"titulo\": \"titulo\",\n" +
           "        \"autor\": \"autor\",\n" +
           "        \"isbn\": \"123-4-56-722101-3\",\n" +
           "        \"genero\": \"fantasia\",\n" +
           "        \"disponibilidade\": \"INDISPONIVEL\"\n" +
           "    },\n" +
           "    {\n" +
           "        \"id\": 13,\n" +
           "        \"titulo\": \"titulo atualizado\",\n" +
           "        \"autor\": \"autor\",\n" +
           "        \"isbn\": \"123-4-56-789101-3\",\n" +
           "        \"genero\": \"genero\",\n" +
           "        \"disponibilidade\": \"DISPONIVEL\"\n" +
           "    }\n" +
           "]";

   public static final String criarUsuarioResponse= "{\n" +
           "    \"id\": 4,\n" +
           "    \"nome\": \"usuario leitor\",\n" +
           "    \"email\": \"leitor@4106985.com\",\n" +
           "    \"senha\": \"$2a$10$CjUPHAJ6Da9naofO9L3i0uASuTh8v7pgrTyJ50vANV7pGnXgLTUJW\",\n" +
           "    \"cargo\": \"LEITOR\"\n" +
           "}";

   public static final String atualizarUsuarioResponse= "{\n" +
           "    \"id\": 4,\n" +
           "    \"nome\": \"leitor\",\n" +
           "    \"email\": \"leitor@4106985.com\",\n" +
           "    \"senha\": \"$2a$10$CjUPHAJ6Da9naofO9L3i0uASuTh8v7pgrTyJ50vANV7pGnXgLTUJW\",\n" +
           "    \"cargo\": \"LEITOR\"\n" +
           "}";

   public static final String obterUsuarioPorIdResponse= "{\n" +
           "    \"id\": 4,\n" +
           "    \"nome\": \"leitor\",\n" +
           "    \"email\": \"leitor@4106985.com\",\n" +
           "    \"senha\": \"$2a$10$CjUPHAJ6Da9naofO9L3i0uASuTh8v7pgrTyJ50vANV7pGnXgLTUJW\",\n" +
           "    \"cargo\": \"LEITOR\"\n" +
           "}";

   public static final String obterTodosUsuariosResponse= "[\n" +
           "    {\n" +
           "        \"id\": 1,\n" +
           "        \"nome\": \"bibliotecario\",\n" +
           "        \"email\": \"bibliotecario@4106985.com\",\n" +
           "        \"senha\": \"$2a$10$LNmwV8EEPTPFW1irTO0lP.bd.2O8gHlz06kvqREWazlPOcffA1jsu\",\n" +
           "        \"cargo\": \"BIBLIOTECARIO\"\n" +
           "    },\n" +
           "    {\n" +
           "        \"id\": 3,\n" +
           "        \"nome\": \"admin\",\n" +
           "        \"email\": \"admin@4106985.com\",\n" +
           "        \"senha\": \"$2a$10$Th4OMj13qR5qa6OoKyIfduGhjtxUBNHNJh1vSpsq4Aonsl8ay14aS\",\n" +
           "        \"cargo\": \"ADMIN\"\n" +
           "    },\n" +
           "    {\n" +
           "        \"id\": 4,\n" +
           "        \"nome\": \"leitor\",\n" +
           "        \"email\": \"leitor@4106985.com\",\n" +
           "        \"senha\": \"$2a$10$CjUPHAJ6Da9naofO9L3i0uASuTh8v7pgrTyJ50vANV7pGnXgLTUJW\",\n" +
           "        \"cargo\": \"LEITOR\"\n" +
           "    }\n" +
           "]";
}