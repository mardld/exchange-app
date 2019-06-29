package mardld.exchange.app.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ExchangeParams {
    private final String currencyFrom;
    private final String currencyTo;
    private final BigDecimal amount;
}
