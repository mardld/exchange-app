package mardld.exchange.app.resolver;

public interface Resolver<T> {

    T resolve(String argument);
}
