package kapur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import kapur.model.Stock;
import kapur.service.TickerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private TickerService service;

    @GetMapping("/history")
    public List<Stock> getHistory() {
        return service.getHistory();
    }

    @GetMapping("/history/{ticker}")
    public Stock getQuote(@PathVariable String ticker) {
        return service.displayStockInfo(ticker);
    }
}
