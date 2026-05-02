package kapur.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kapur.model.Stock;

public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findAll();
    List<Stock> findByTicker(String ticker);
    List<Stock> findByTickerOrderByTimestampDesc(String ticker);
}
