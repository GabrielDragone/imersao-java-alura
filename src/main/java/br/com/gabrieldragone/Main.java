package br.com.gabrieldragone;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Fazer conexão HTTP (Protocoloco de comunicação na internet) e buscar os top 250 filmes:
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm"; // Tá fora do ar
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //System.out.println("Response:");
        //System.out.println(body);

        // Extrair/Parsear só os dados que interessam (Título, poster, classificação):
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);

        System.out.println();
        System.out.println("Top 250 Filmes:");
        System.out.println();
        //System.out.println(listaDeFilmes.get(0));

        // Exibir e manipular os dados:
        for (Map<String, String> filme: listaDeFilmes) {
            System.out.println("Posição: " + filme.get("rank")); // Na estrutura Map, quando queremos pegar algum valor, utilizamos o get(key).
            System.out.println("Título: " + filme.get("title"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println("Avaliação: " + filme.get("imDbRating"));
            System.out.println();
        }

    }
}