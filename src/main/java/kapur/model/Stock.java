package kapur.model;

import java.time.Instant;

public class Stock {

    /**
     * FinnHub API returns the following
     * {"c":261.74,"h":263.31,"l":260.68,"o":261.07,"pc":259.45,"t":1582641000}
     * c = current price, h = high price of the day, l = low price, o = open, pc =
     * previous close, t = timestamp
     */

    private String ticker;
    private double current;
    private double high;
    private double low;
    private double open;
    private double previousClose;
    private Instant timestamp;

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

    public double getOpen() {
        return open;
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

    public void setOpen(double open) {
        this.open = open;
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
                + " | Low: " + this.low + " | Open: " + this.open + " | Prev Close: " + this.previousClose
                + " | Timestamp: " + this.timestamp;
    }

}