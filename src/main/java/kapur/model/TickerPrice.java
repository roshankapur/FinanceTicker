package kapur.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

/**
 * TickerPrice is a "screenshot" of data received from the API at any given timestamp
 * EXAMPLE: AAPL, STOCK, 123.55, 126.57, 91.05,
 * fields based off of https://finnhub.io/docs/api#websocket-trades fields
 * change later
 * also change Instant to other Java date-timestamp
 */
public record TickerPrice(
        String ticker,
        AssetClass type,
        BigDecimal LastPrice,
        BigDecimal high,
        BigDecimal low,
        Instant timestamp,
        BigInteger Volume
        )
{}
