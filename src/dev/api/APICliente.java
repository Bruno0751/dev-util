package dev.api;

import dev.dto.ClienteDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 19/04/2025
 */
public class APICliente {

    public static String requestGET() {
        try {
            URL url = new URL("http://localhost:1010/v1/cliente/");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(false); // Permite enviar body
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
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
            System.out.println("Código de resposta: " + responseCode);
            connection.disconnect();
            return response.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static String requestPOST(ClienteDto cliente) throws IOException {
        try {
            URL url = new URL("http://localhost:1010/v1/cliente/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true); // Permite enviar body
            connection.setDoInput(true);

            StringBuilder stringBuilder = new StringBuilder("{");
            stringBuilder.append("\"name\": \"").append(cliente.getName()).append("\", ");
            stringBuilder.append("\"old_year\": \"").append(cliente.getOldYear()).append("\", ");
            stringBuilder.append("\"height\": \"").append(cliente.getHeight()).append("\"");
            stringBuilder.append("}");
            String dto = stringBuilder.toString();
            stringBuilder.setLength(0);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = dto.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            System.out.println("Código de resposta: " + connection.getResponseCode());
            connection.disconnect();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException(e.getMessage());
        }
        return null;
    }

    public static String requestPUT(ClienteDto cliente) throws IOException {
        try {
            URL url = new URL("http://localhost:1010/v1/cliente/123");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true); // Permite enviar body
            connection.setDoInput(true);
            
            StringBuilder stringBuilder = new StringBuilder("{");
            stringBuilder.append("\"name\": \"").append(cliente.getName()).append("\", ");
            stringBuilder.append("\"old_year\": \"").append(cliente.getOldYear()).append("\", ");
            stringBuilder.append("\"height\": \"").append(cliente.getHeight()).append("\"");
            stringBuilder.append("}");
            String dto = stringBuilder.toString();
            stringBuilder.setLength(0);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = dto.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            System.out.println("Código de resposta: " + connection.getResponseCode());
            connection.disconnect();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException(e.getMessage());
        }
        return null;
    }

    public static String requestDELETE(String id) throws MalformedURLException, IOException {
        URL url = null;
        try {
            url = new URL("http://localhost:1010/v1/cliente/" + id); // URL de exemplo
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            throw new MalformedURLException(e.getMessage());
        }
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true); // Permite enviar body
            connection.setDoInput(true);
            System.out.println("Código de resposta: " + connection.getResponseCode());
            connection.disconnect();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException(e.getMessage());
        }
        return null;
    }
}
