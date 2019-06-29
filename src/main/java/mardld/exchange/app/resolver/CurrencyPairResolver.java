package mardld.exchange.app.resolver;

import mardld.exchange.app.exception.UsageExchangerException;
import mardld.exchange.app.model.CurrencyPair;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

@Component
public class CurrencyPairResolver implements Resolver<CurrencyPair> {

    @Override
    public CurrencyPair resolve(String argument) {
        List<String> currencies = Arrays.asList(argument.split("/", 2));
        validateCurrenciesCount(currencies);
        currencies.forEach(this::validateCurrencyCodeFormat);
        return new CurrencyPair(currencies.get(1), currencies.get(0));
    }

    private void validateCurrenciesCount(List<String> currencies) {
        if (currencies.size() != 2) {
            throw new UsageExchangerException();
        }
    }

    private void validateCurrencyCodeFormat(String currency) {
        try {
            Currency.getInstance(currency);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new UsageExchangerException();
        }
    }
}
