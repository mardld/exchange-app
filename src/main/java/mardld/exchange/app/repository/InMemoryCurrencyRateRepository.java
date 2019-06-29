package mardld.exchange.app.repository;

import mardld.exchange.app.exception.RateNotFoundException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryCurrencyRateRepository implements CurrencyRateProvider, SupportedCurrenciesProvider {

    private static final Map<String, BigDecimal> EXCHANGE_RATES;

    static {
        EXCHANGE_RATES = new HashMap<>();
        EXCHANGE_RATES.put("DKK", new BigDecimal("100"));
        EXCHANGE_RATES.put("EUR", new BigDecimal("743.94"));
        EXCHANGE_RATES.put("USD", new BigDecimal("663.11"));
        EXCHANGE_RATES.put("GBP", new BigDecimal("852.85"));
        EXCHANGE_RATES.put("SEK", new BigDecimal("76.10"));
        EXCHANGE_RATES.put("NOK", new BigDecimal("78.40"));
        EXCHANGE_RATES.put("CHF", new BigDecimal("683.58"));
        EXCHANGE_RATES.put("JPY", new BigDecimal("5.9740"));
    }

    @Override
    public BigDecimal getRate(String currency) {
        BigDecimal rate = EXCHANGE_RATES.get(currency);
        if (rate == null) {
            throw new RateNotFoundException(currency);
        }
        return rate;
    }

    @Override
    public List<String> getSupportedCurrencies() {
        return new ArrayList<>(EXCHANGE_RATES.keySet());
    }
}
