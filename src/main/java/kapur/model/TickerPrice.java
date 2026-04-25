package kapur.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

/**
 * TickerPrice is a "screenshot" of data received from the API at any given timestamp
 *
 * fields based off of https://finnhub.io/docs/api#websocket-trades fields
 * change later
 */
public record TickerPrice(
        String ticker,
        String assetClass,
        BigDecimal LastPrice,
        BigDecimal high,
        BigDecimal low,
        Instant timestamp,
        BigInteger Volume
        )
{}
