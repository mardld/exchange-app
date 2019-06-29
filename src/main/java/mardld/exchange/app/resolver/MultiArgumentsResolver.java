package mardld.exchange.app.resolver;

public interface MultiArgumentsResolver<T> {

    T resolve(String ... args);
}
