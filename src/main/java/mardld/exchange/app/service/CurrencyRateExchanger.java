package mardld.exchange.app.service;

import lombok.RequiredArgsConstructor;
import mardld.exchange.app.model.ExchangeParams;
import mardld.exchange.app.repository.CurrencyRateProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
@RequiredArgsConstructor
public class CurrencyRateExchanger {

    private final CurrencyRateProvider rateRepository;

    public BigDecimal exchange(ExchangeParams params) {
        BigDecimal rateFrom = rateRepository.getRate(params.getCurrencyFrom());
        BigDecimal rateTo = rateRepository.getRate(params.getCurrencyTo());
        BigDecimal amount = params.getAmount();

        return rateTo.multiply(amount).divide(rateFrom, MathContext.DECIMAL64).stripTrailingZeros();
    }

}
