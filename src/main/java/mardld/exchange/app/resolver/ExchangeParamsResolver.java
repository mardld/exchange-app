package mardld.exchange.app.resolver;

import lombok.RequiredArgsConstructor;
import mardld.exchange.app.model.ExchangeParams;
import mardld.exchange.app.exception.UsageExchangerException;
import mardld.exchange.app.model.CurrencyPair;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ExchangeParamsResolver implements MultiArgumentsResolver<ExchangeParams> {

    private final Resolver<CurrencyPair> currencyPairResolver;
    private final Resolver<BigDecimal> amountResolver;

    @Override
    public ExchangeParams resolve(String... args) {
        if (args.length != 2) {
            throw new UsageExchangerException();
        }
        CurrencyPair pair = currencyPairResolver.resolve(args[0]);
        BigDecimal amount = amountResolver.resolve(args[1]);

        return new ExchangeParams(pair.getFrom(), pair.getTo(), amount);
    }

}
