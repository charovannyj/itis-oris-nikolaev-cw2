package com.solncev.fx.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;


public class HttpClientImpl implements HttpClient {

    static String getUrl = "https://jsonplaceholder.typicode.com/posts";
    static String postUrl = "https://gorest.co.in/public/v1/users";
    static Map<String, String> user;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        user = new HashMap<>();

        String name = sc.nextLine();
        user.put("name", name);

        String email = sc.nextLine();
        user.put("email", email);

        String gender = sc.nextLine();
        user.put("gender", gender);

        String status = sc.nextLine();
        user.put("status", status);
        new HttpClientImpl().post(HttpClientImpl.getUrl, user);
    }

    @Override
    public String get(String url, Map<String, String> params) {
        String answer;
        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                answer = content.toString();
                System.out.println(answer);
            }

            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }

    @Override
    public String post(String url, Map<String, String> params) {
        String answer;
        try {
            URL postUrl = new URL(url);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setRequestProperty("Authorization", "Bearer 40832c29289594cb2ac13c96e49dc93dd6e028a6d63414efaeefdcb7b4de12dd");

            postConnection.setDoOutput(true);

            Gson gson = new Gson();
            String json = gson.toJson(params);

            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println(postConnection.getResponseCode());

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                answer = content.toString();

                System.out.println(answer);
            }
            postConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answer;

    }

    @Override
    public String put(String url, Map<String, String> params) {
        String answer;
        try {
            URL postUrl = new URL(url);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setRequestMethod("PUT");
            for (Object key : params.keySet()) {
                postConnection.setRequestProperty(key.toString(), params.get(key));
            }

            postConnection.setDoOutput(true);

            Gson gson = new Gson();
            String json = gson.toJson(params);
            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println(postConnection.getResponseCode());

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                answer = content.toString();
                System.out.println(answer);
            }
            postConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }

    @Override
    public String delete(String url, Map<String, String> params) {
        try {
            URL postUrl = new URL(url);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setRequestMethod("DELETE");
            for (Object key : params.keySet()) {
                postConnection.setRequestProperty(key.toString(), params.get(key));
            }

            postConnection.setDoOutput(true);

            System.out.println(postConnection.getResponseCode());

            postConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
