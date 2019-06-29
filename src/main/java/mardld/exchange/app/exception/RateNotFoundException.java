package mardld.exchange.app.exception;

public class RateNotFoundException extends ExchangerException {

    public RateNotFoundException(String currency) {
        super(currency + " rate does not exist");
    }
}
