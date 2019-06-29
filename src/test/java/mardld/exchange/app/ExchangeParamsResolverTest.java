package mardld.exchange.app;

import mardld.exchange.app.exception.UsageExchangerException;
import mardld.exchange.app.model.CurrencyPair;
import mardld.exchange.app.model.ExchangeParams;
import mardld.exchange.app.resolver.AmountResolver;
import mardld.exchange.app.resolver.CurrencyPairResolver;
import mardld.exchange.app.resolver.ExchangeParamsResolver;
import mardld.exchange.app.resolver.Resolver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExchangeParamsResolverTest {

    @Mock
    private Resolver<CurrencyPair> currencyPairResolver;

    @Mock
    private Resolver<BigDecimal> amountResolver;

    private ExchangeParamsResolver resolver;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        resolver = new ExchangeParamsResolver(currencyPairResolver, amountResolver);
    }

    @Test
    public void resolve_happy_path() {
        String currencyPairAsText = "EUR/DKK";
        String from = "DKK";
        String to = "EUR";
        String amount = "5.0005";
        when(currencyPairResolver.resolve(currencyPairAsText)).thenReturn(new CurrencyPair(from, to));
        when(amountResolver.resolve(amount)).thenReturn(new BigDecimal(amount));
        assertThat(resolver.resolve(currencyPairAsText, amount),
                equalTo(new ExchangeParams(from, to, new BigDecimal(amount))));

        verify(currencyPairResolver).resolve(currencyPairAsText);
        verify(amountResolver).resolve(amount);
    }

    @Test(expected = UsageExchangerException.class)
    public void resolve_fails_if_amount_missing() {
        resolver.resolve("EUR/DKK");
    }

    @Test(expected = UsageExchangerException.class)
    public void resolve_fails_if_currency_missing() {
        resolver.resolve("1000");
    }

    @Test(expected = UsageExchangerException.class)
    public void resolve_fails_if_amount_resolver_fails() {
        when(amountResolver.resolve(anyString())).thenThrow(new UsageExchangerException());
        resolver.resolve("EUR/DKK", "C");
    }

    @Test(expected = UsageExchangerException.class)
    public void resolve_fails_if_currency_resolver_fails() {
        when(currencyPairResolver.resolve(anyString())).thenThrow(new UsageExchangerException());
        resolver.resolve("EUR-DKK", "5");
    }
}
