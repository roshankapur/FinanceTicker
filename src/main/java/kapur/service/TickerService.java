package kapur.service;

import org.springframework.stereotype.Service;
import kapur.client.FinanceClient;
import kapur.model.Stock;
import kapur.repository.StockRepository;
import java.util.List;

@Service
public class TickerService {
    private FinanceClient client;
    private StockRepository repository;

    // hardcoded ticker list, eventually turn to private static String[] with an
    // ability to add or remove tickers
    private static final String[] DEFAULT_TICKERS = { "AAPL", "MSFT", "GOOGL", "AMZN", "META" };

    public TickerService(FinanceClient client, StockRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    /**
     * Controller layer calls method to fetch a refresh of all price indicators for
     * each stock. Eventually this method will update the database through the
     * Controller layer
     */
    public List<Stock> getHistory() {
        for (String ticker : DEFAULT_TICKERS) {
            Stock stock = client.fetchStockQuote(ticker);
            if (stock != null) {
                repository.save(stock);
            }
        }
        return repository.findAll();
    }

    public Stock displayStockInfo(String ticker) {
        if (this.client == null) {
            throw new RuntimeException("Client not initialized");
        }
        Stock stock = this.client.fetchStockQuote(ticker);
        if (stock.getTicker() == null) {
            System.out.println("Ticker not found: " + ticker);
        }
        repository.save(stock);
        System.out.println(stock);
        return stock;
    }

    public FinanceClient getClient() {
        return client;
    }

}
