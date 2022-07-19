package br.com.gabrieldragone;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    // Usamos o Pattern para realizar uma Expressão Regular no Java.
    // Copiar os trechos de dentro do compile, pegar o body e jogar no site https://regex101.com/ para testar.
    // Dentro dos () temos os grupos de captura
    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*"); // Elimina a parte de itens e errorMessage e pega apenas os filmes.
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\""); // Pega o padrão chave:valor de cada filme.

    public List<Map<String, String>> parse(String json) {

        Matcher matcher = REGEX_ITEMS.matcher(json); // Forma de chamar a expressão para pegar apenas os filmes.

        if (!matcher.find()) {
            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{"); // Separa cada um dos filmes },{

        List<Map<String, String>> dados = new ArrayList<>();

        for (String item : items) {
            Map<String, String> atributosItem = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }

            dados.add(atributosItem);
        }

        return dados;
    }

}
