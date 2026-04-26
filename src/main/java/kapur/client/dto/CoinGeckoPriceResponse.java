package kapur.client.dto;

import java.math.BigDecimal;

public record CoinGeckoPriceResponse(
        BigDecimal usd,
        BigDecimal usd_24h_change
) {}
