package kapur.client;

import kapur.model.Stock;
import java.net.http.*;
import java.net.*;
import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
public class FinanceClient {

    @Value("${api.finnhubkey}")
    private String apiKey;
    private final HttpClient finnhubClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    private final ObjectMapper mapper = new ObjectMapper();

    public Stock fetchStockQuote(String ticker) {
        String baseUrl = "https://finnhub.io/api/v1/quote?symbol=" + ticker + "&token=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .GET()
                .build();
        try {
            HttpResponse<String> response = finnhubClient.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode node = mapper.readTree(response.body());
            Stock stock = new Stock();

            stock.setTicker(ticker);
            stock.setCurrent(node.path("c").asDouble());
            stock.setHigh(node.path("h").asDouble());
            stock.setLow(node.path("l").asDouble());
            stock.setOpen(node.path("o").asDouble());
            stock.setPreviousClose(node.path("pc").asDouble());
            stock.setTimestamp(Instant.ofEpochSecond(node.path("t").asLong()));

            return stock;

        } catch (Exception e) {
            System.out.println("Error fetching price for Stock: " + ticker + "\n" + e.getMessage());
            return null;
        }

    }
}
