package kapur.client;

import kapur.model.TickerPrice;
import tools.jackson.databind.ObjectMapper;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CoinGeckoClient {
    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    /**httpclient and objectmapper classes have
     * weird assignment/setters/initialisation procedures
     */

    /** //2.1.4
    public CompletableFuture<List<TickerPrice>> fetchPrices(List<String> symbols){

    }
    //2.1.5
    public HttpRequest buildRequest(List<String> symbols){

    }
    //2.1.6
    public List<TickerPrice> parseResponse(String json, List<String> symbols) {

    }
    */

}
