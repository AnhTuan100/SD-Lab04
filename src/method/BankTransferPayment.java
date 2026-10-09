package method;

import core.PaymentStrategy;

import java.math.BigDecimal;

public class BankTransferPayment implements PaymentStrategy {
    private final String accountNumber;

    public BankTransferPayment(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Thanh toán bằng chuyển khoản (TK " + accountNumber + "): " + amount);
    }

    @Override
    public String getName() { return "BANK_TRANSFER"; }
}