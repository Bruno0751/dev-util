package dev.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 19/04/2025
 */
public class Util {

    public static String zeroFill(String value, int lopp) {
        String zero = "";
        while (lopp != 0) {
            zero += "0";
            lopp--;
        }
        value = value + zero;
        return value;
    }

    public static String taxarText(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : text.toCharArray()) {
            stringBuilder.append(c).append('\u0336');
        }
        text = stringBuilder.toString();
        stringBuilder.setLength(0);
        return text;
    }

    public static String getCEP(String cep) {
        try {
//            URL url = new URL("https://http.cat/" + 100);
//            URL url = new URL("https://cdn.apicep.com/file/apicep/" + 91170 + "-090.json");
//            URL url = new URL("https://brasilapi.com.br/api/cep/v2/" + 91170090);
//            URL url = new URL("https://opencep.com/v1/" + 91170090);
//            URL url = new URL("https://brasilaberto.com/api/v1/zipcode/" + 91170090);
//            URL url = new URL("https://comercial.cnpj.ws/cnpj/27865757000102?token=UszgJdaXlQG5xHtN9zuhWTnkAkgA1l5WsuOQL251EB6I");
//            URL url = new URL("https://publica.cnpj.ws/cnpj/27865757000102");
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("Content-Type", "application/xml");
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("Content-Type", "multipart/form-data");
//            connection.setRequestProperty("Content-Type", "text/plain");
//            connection.setRequestProperty("Content-Type", "text/html");
//            connection.setRequestProperty("Content-Type", "application/octet-stream");
            connection.connect();
            int responseCode = connection.getResponseCode();
            StringBuilder response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            System.out.println("URL: " + url.toString());
            System.out.println("Código de resposta: " + responseCode);
            System.out.println("Resposta: " + response.toString());
            connection.disconnect();
            return response.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static String getJSON(String json) {
        StringBuilder formatado = new StringBuilder();
        int nivel = 0;
        boolean emString = false;
        for (int i = 0; i < json.length(); i++) {
            char ch = json.charAt(i);
            switch (ch) {
                case '"':
                    formatado.append(ch);
                    if (i > 0 && json.charAt(i - 1) != '\\') {
                        emString = !emString;
                    }
                    break;
                case '{':
                case '[':
                    formatado.append(ch);
                    if (!emString) {
                        formatado.append("\n");
                        nivel++;
                        formatado.append(repetirEspacos(nivel));
                    }
                    break;

                case '}':
                case ']':
                    if (!emString) {
                        formatado.append("\n");
                        nivel--;
                        formatado.append(repetirEspacos(nivel));
                        formatado.append(ch);
                    } else {
                        formatado.append(ch);
                    }
                    break;

                case ',':
                    formatado.append(ch);
                    if (!emString) {
                        formatado.append("\n");
                        formatado.append(repetirEspacos(nivel));
                    }
                    break;

                case ':':
                    if (!emString) {
                        formatado.append(": ");
                    } else {
                        formatado.append(ch);
                    }
                    break;

                default:
                    formatado.append(ch);
                    break;
            }
        }

        return formatado.toString();
    }
    
    // Compatível com Java 8
    private static String repetirEspacos(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("  "); // dois espaços por nível
        }
        return sb.toString();
    }
}
