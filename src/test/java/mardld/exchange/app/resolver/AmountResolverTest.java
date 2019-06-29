package mardld.exchange.app.resolver;

import mardld.exchange.app.exception.UsageExchangerException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class AmountResolverTest {

    @Test
    public void resolve_happy_path() {
        assertThat(new AmountResolver().resolve("15"), equalTo(new BigDecimal("15")));
    }

    @Test(expected = UsageExchangerException.class)
    public void resolve_throws_usage_exception_if_parse_fails() {
        new AmountResolver().resolve("A");
    }
}