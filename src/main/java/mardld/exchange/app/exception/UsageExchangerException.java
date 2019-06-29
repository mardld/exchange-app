package mardld.exchange.app.exception;

public class UsageExchangerException extends ExchangerException {

    public UsageExchangerException() {
        super("Usage: Exchange <currency pair> <amount to exchange>");
    }
}
