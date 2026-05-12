package kapur.service;

import org.springframework.stereotype.Service;
import kapur.client.FinanceClient;
import kapur.model.Stock;
import kapur.repository.StockRepository;
import java.util.ArrayList;
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

    // Fetch fresh quotes from finnhub and save to DB.
    public void refreshPrices() {
        for (String ticker : DEFAULT_TICKERS) {
            Stock stock = client.fetchStockQuote(ticker);
            if (stock != null) {
                repository.save(stock);
            }
        }
    }

    // Return most recent quote for each ticker from DB
    public List<Stock> getLatestPrices() {
        List<Stock> latest = new ArrayList<>();
        for (String ticker : DEFAULT_TICKERS) {
            Stock stock = repository.findFirstByTickerOrderByTimestampDesc(ticker);
            if (stock != null) {
                latest.add(stock);
            }
        }
        return latest;
    }

    // Return all stored stock history instead of calling finnhub
    public List<Stock> getHistory() {
        return repository.findAll();
    }

    // Return history for a specific ticker from the database.
    public List<Stock> getTickerHistory(String ticker) {
        return repository.findByTickerOrderByTimestampDesc(ticker);
    }

    // Fetches and saves a single ticker's quote and returns null if API call failed
    public Stock fetchAndSaveQuote(String ticker) {
        Stock stock = client.fetchStockQuote(ticker);
        if (stock == null || stock.getTicker() == null) {
            return null;
        }
        repository.save(stock);
        return stock;
    }

    public FinanceClient getClient() {
        return client;
    }

}
