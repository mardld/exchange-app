package mardld.exchange.app;

import mardld.exchange.app.repository.SupportedCurrenciesProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ExchangeApp.class)
public class ExchangeAppTest {

    @Autowired
    private ExchangeApp app;

    @Autowired
    private SupportedCurrenciesProvider currenciesProvider;

    @Test
    public void resolveArgsAndExchange_all_supported_currencies_combos() {
        List<String> currencies = currenciesProvider.getSupportedCurrencies();
        currencies.forEach(from -> {
            currencies.forEach(to -> {
                app.resolveArgsAndExchange(String.format("%s/%s", to, from), "10");
            });
        });
    }
}
