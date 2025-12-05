package com_mateo_conversor.api;

import com.google.gson.Gson;
import com_mateo_conversor.api.dto.ExchangeRateResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ExchangeRateApiClient {

    private static final String API_KEY = "d6b5685f1899b703c9495e4b";

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final HttpClient client;

    public ExchangeRateApiClient() {
        this.client = HttpClient.newHttpClient();
    }

    //Método que consume la API y devuelve el JSON como texto
    public ExchangeRateResponse fetchRates(String baseCurrency) {

        String urlString = BASE_URL + API_KEY + "/latest/" + baseCurrency;
        URI url = URI.create(urlString);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("API returned status code: " + response.statusCode());
            }

            String json = response.body();
            // JSON → ExchangeRateResponse
            return new Gson().fromJson(json, ExchangeRateResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("Error calling exchange rate API", e);
        }
    }

}
