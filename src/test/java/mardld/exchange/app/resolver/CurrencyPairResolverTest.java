package mardld.exchange.app.resolver;

import mardld.exchange.app.exception.UsageExchangerException;
import mardld.exchange.app.model.CurrencyPair;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CurrencyPairResolverTest {

    @Test
    public void resolve() {
        assertThat(new CurrencyPairResolver().resolve("EUR/DKK"), equalTo(new CurrencyPair("DKK", "EUR")));
    }

    @Test(expected = UsageExchangerException.class)
    public void resolve_fails_missing_separator() {
        new CurrencyPairResolver().resolve("AB");
    }

    @Test(expected = UsageExchangerException.class)
    public void resolve_fails_missing_from() {
        new CurrencyPairResolver().resolve("A/");
    }

    @Test(expected = UsageExchangerException.class)
    public void resolve_fails_missing_to() {
        new CurrencyPairResolver().resolve("/B");
    }

}