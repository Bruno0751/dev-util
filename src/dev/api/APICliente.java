package dev.api;

import dev.dto.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
            connection.setDoOutput(false);
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
        return null;
    }

    public static void requestPOST(Cliente cliente) {
        try {
            URL url = new URL("http://localhost:1010/v1/cliente/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(false);

            StringBuilder stringBuilder = new StringBuilder("{");
            stringBuilder.append("\"name\": \"").append(cliente.getName()).append("\", ");
            stringBuilder.append("\"oldYear\": \"").append(cliente.getOldYear()).append("\", ");
            stringBuilder.append("\"heigth\": \"").append(cliente.getHeigth()).append("\"");
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
        }
    }
}
