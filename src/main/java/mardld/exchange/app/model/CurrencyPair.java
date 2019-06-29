package mardld.exchange.app.model;

import lombok.Value;

@Value
public class CurrencyPair {
    private String from;
    private String to;
}
