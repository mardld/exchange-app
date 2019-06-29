package mardld.exchange.app.repository;

import java.math.BigDecimal;

public interface CurrencyRateProvider {

    BigDecimal getRate(String currency);
}
