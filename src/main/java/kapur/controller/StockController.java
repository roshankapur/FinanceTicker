package kapur.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import kapur.model.Stock;
import kapur.service.TickerService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final TickerService service;

    public StockController(TickerService service) {
        this.service = service;
    }

    @GetMapping("/history")
    public List<Stock> getHistory() {
        return service.getHistory();
    }

    @GetMapping("/history/{ticker}")
    public List<Stock> getTickerHistory(@PathVariable String ticker) {
        return service.getTickerHistory(ticker);
    }

    @PostMapping("/refresh")
    public List<Stock> refresh() {
        service.refreshPrices();
        return service.getLatestPrices();
    }
}
