package kapur.service;

import org.springframework.stereotype.Service;

import kapur.client.FinanceClient;
import kapur.model.Stock;

@Service
public class TickerService {
    private FinanceClient client;

    public TickerService(FinanceClient client) {
        this.client = client;
    }

    public void displayStockInfo(String ticker) {
        if (this.client == null) {
            throw new RuntimeException("Client not initialized");
        }
        Stock stock = this.client.fetchStockQuote(ticker);
        if (stock.getTicker() == null) {
            System.out.println("Ticker not found: " + ticker);
        }
        System.out.println(stock);
    }

    public FinanceClient getClient() {
        return client;
    }

}
