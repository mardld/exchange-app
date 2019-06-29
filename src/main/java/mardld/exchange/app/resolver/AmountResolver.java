package mardld.exchange.app.resolver;

import mardld.exchange.app.exception.UsageExchangerException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmountResolver implements Resolver<BigDecimal> {

    @Override
    public BigDecimal resolve(String argument) {
        try {
            return new BigDecimal(argument);
        } catch (NumberFormatException e) {
            throw new UsageExchangerException();
        }
    }
}
