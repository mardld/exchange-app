package mardld.exchange.app;

import mardld.exchange.app.exception.ExchangerException;
import mardld.exchange.app.repository.InMemoryCurrencyRateRepository;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class InMemoryCurrencyRateProviderTest {

    private InMemoryCurrencyRateRepository repository = new InMemoryCurrencyRateRepository();

    @Test
    public void getRate_returnsEurRate() {
        assertThat(repository.getRate("EUR"), equalTo(new BigDecimal("743.94")));
    }

    @Test(expected = ExchangerException.class)
    public void getRate_throwsExceptionForNullCurrency() {
        repository.getRate(null);
    }

    @Test(expected = ExchangerException.class)
    public void getRate_throwsExceptionForUnknownCurrency() {
        repository.getRate("ABC");
    }
}
