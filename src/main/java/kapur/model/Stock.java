package kapur.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {

    /**
     * FinnHub API returns the following
     * {"c":261.74,"h":263.31,"l":260.68,"o":261.07,"pc":259.45,"t":1582641000}
     * c = current price, h = high price of the day, l = low price, o = open, pc =
     * previous close, t = timestamp
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;
    private double current;
    private double high;
    private double low;
    private double openPrice;
    private double previousClose;
    private Instant timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCurrent() {
        return current;
    }

    public String getTicker() {
        return ticker;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Ticker: " + this.ticker + " | Current: " + this.current + " | High: " + this.high
                + " | Low: " + this.low + " | Open: " + this.openPrice + " | Prev Close: " + this.previousClose
                + " | Timestamp: " + this.timestamp;
    }

}