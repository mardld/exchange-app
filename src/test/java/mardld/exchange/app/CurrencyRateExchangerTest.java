package mardld.exchange.app;

import mardld.exchange.app.model.ExchangeParams;
import mardld.exchange.app.repository.CurrencyRateProvider;
import mardld.exchange.app.service.CurrencyRateExchanger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class CurrencyRateExchangerTest {

    @Mock
    private CurrencyRateProvider rateRepository;

    @InjectMocks
    private CurrencyRateExchanger exchanger;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void exchange() {
        ExchangeParams params = new ExchangeParams("DKK", "EUR", new BigDecimal("7"));
        when(rateRepository.getRate("DKK")).thenReturn(new BigDecimal("2"));
        when(rateRepository.getRate("EUR")).thenReturn(new BigDecimal("5"));
        BigDecimal actual = exchanger.exchange(params);
        assertThat(actual, equalTo(new BigDecimal("17.5")));
    }
}
