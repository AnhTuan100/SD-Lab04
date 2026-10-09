package method;

import core.PaymentStrategy;
import core.Refundable;
import core.Verifiable;

import java.math.BigDecimal;

public class CreditCardPayment implements PaymentStrategy, Refundable, Verifiable {
    private final String cardNumber;
    private final String holderName;

    public CreditCardPayment(String cardNumber, String holderName) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
    }

    @Override
    public boolean verify() {
        System.out.println("Xác thực 3D-Secure/OTP cho thẻ " + mask(cardNumber));
        return true;
    }

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Thanh toán bằng thẻ tín dụng (" + holderName + "): " + amount);
    }

    @Override
    public void refund(BigDecimal amount) {
        System.out.println("Hoàn tiền về thẻ tín dụng: " + amount);
    }

    @Override
    public String getName() { return "CREDIT_CARD"; }

    private String mask(String n) {
        return "**** **** **** " + n.substring(n.length() - 4);
    }
}