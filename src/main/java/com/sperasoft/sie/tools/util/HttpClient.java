package com.sperasoft.sie.tools.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ilya K on 7/19/2017.
 */
public class HttpClient {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String APPLICATION_TYPE = "application/xml";
    private final int CONNECTION_TIMEOUT = 100000;
    private final int READ_TIMEOUT = 100000;

    private String response;

    int get( String url ) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Content-Type", APPLICATION_TYPE);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);

        int responseCode = connection.getResponseCode();

        this.setResponse( this.readResponseFromConnection(connection) );

        return responseCode;
    }
    int post( String url, String payload ) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Content-Type", APPLICATION_TYPE);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);

        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(payload);
        wr.flush();
        wr.close();

        int responseCode = connection.getResponseCode();

        this.setResponse( this.readResponseFromConnection(connection) );

        return responseCode;
    }
    int put( String url, String payload ) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Content-Type", APPLICATION_TYPE);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);

        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(payload);
        wr.flush();
        wr.close();

        int responseCode = connection.getResponseCode();

        this.setResponse( this.readResponseFromConnection(connection) );

        return responseCode;
    }

    public String getResponse() {
        return this.response;
    }

    private void setResponse(String response) {
        this.response = response;
    }

    private String readResponseFromConnection(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String output;
        StringBuilder response = new StringBuilder();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        return response.toString();
    }
}
