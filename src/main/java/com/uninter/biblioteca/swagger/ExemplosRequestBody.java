package com.uninter.biblioteca.swagger;

public class ExemplosRequestBody {

    public static final String criarEmprestimoBody="{\n" +
            "    \"usuario_id\": 1,\n" +
            "    \"livro_id\": 1,\n" +
            "    \"data_devolucao\": \"01-01-2025\"\n" +
            "}";

    public static final String atualizarEmprestimoBody="{\n" +
            "    \"usuario_id\": \"4\", \n" +
            "    \"data_devolucao\": \"02-03-2025\"\n" +
            "}";

    public static final String criarLivroBody= "{\n" +
            "    \"titulo\": \"titulo\",\n" +
            "    \"autor\": \"autor\",\n" +
            "    \"isbn\": \"123-4-56-789101-3\",\n" +
            "    \"genero\": \"genero\"\n" +
            "}";

    public static final String atualizarLivroBody= "{\n" +
            "    \"titulo\": \"titulo atualizado\"\n" +
            "}";

    public static final String criarUsuarioBody="{\n" +
            "    \"nome\": \"usuario leitor\",\n" +
            "    \"email\": \"leitor@4106985.com\",\n" +
            "    \"senha\": \"leitor\",\n" +
            "    \"cargo\": \"LEITOR\"\n" +
            "}";

    public static final String atualizarUsuarioBody= "{\n" +
            "    \"nome\": \"leitor\"\n" +
            "}";
}
