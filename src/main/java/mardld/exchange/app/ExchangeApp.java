package mardld.exchange.app;

import lombok.RequiredArgsConstructor;
import mardld.exchange.app.exception.ExchangerException;
import mardld.exchange.app.model.ExchangeParams;
import mardld.exchange.app.resolver.ExchangeParamsResolver;
import mardld.exchange.app.service.CurrencyRateExchanger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@RequiredArgsConstructor
@SpringBootApplication
public class ExchangeApp implements CommandLineRunner {

    private final ExchangeParamsResolver exchangeParamsResolver;
    private final CurrencyRateExchanger rateExchanger;

    public static void main(String... args) {
        SpringApplication.run(ExchangeApp.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            System.out.println(resolveArgsAndExchange(args));
        } catch (ExchangerException e) {
            System.out.println(e.getMessage());
        }
    }

    BigDecimal resolveArgsAndExchange(String... args) {
        ExchangeParams params = exchangeParamsResolver.resolve(args);
        return rateExchanger.exchange(params);
    }
}
