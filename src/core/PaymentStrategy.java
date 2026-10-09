package core;

import java.math.BigDecimal;

public interface PaymentStrategy {
    void pay(BigDecimal amount);
    String getName();
}