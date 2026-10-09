package service;

import core.PaymentStrategy;
import core.Refundable;
import core.Verifiable;

import java.math.BigDecimal;

public class PaymentService {
    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(PaymentStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Phương thức thanh toán không hợp lệ");
        }
        this.strategy = strategy;
    }

    public void checkout(BigDecimal amount) {
        // Chỉ xác thực nếu phương thức này CÓ khả năng xác thực (ISP)
        if (strategy instanceof Verifiable v && !v.verify()) {
            throw new IllegalStateException("Xác thực thất bại");
        }
        strategy.pay(amount);
    }

    public void refund(BigDecimal amount) {
        if (strategy instanceof Refundable r) {
            r.refund(amount);
        } else {
            throw new UnsupportedOperationException(
                    strategy.getName() + " không hỗ trợ hoàn tiền tự động");
        }
    }
}