package method;

import core.PaymentStrategy;
import core.Refundable;
import core.Verifiable;

import java.math.BigDecimal;

public class EWalletPayment implements PaymentStrategy, Refundable, Verifiable {
    private final String phone;

    public EWalletPayment(String phone) { this.phone = phone; }

    @Override
    public boolean verify() {
        System.out.println("Xác thực mã PIN ví " + phone);
        return true;
    }

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Thanh toán bằng ví điện tử (" + phone + "): " + amount);
    }

    @Override
    public void refund(BigDecimal amount) {
        System.out.println("Hoàn tiền về ví điện tử: " + amount);
    }

    @Override
    public String getName() { return "E_WALLET"; }
}