import core.PaymentStrategy;
import method.BankTransferPayment;
import method.CreditCardPayment;
import method.EWalletPayment;
import method.PayPalPayment;
import service.PaymentService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        // 1. Thanh toán bằng thẻ tín dụng
        PaymentService service = new PaymentService(
                new CreditCardPayment("1234567812345678", "Nguyen Van A"));
        service.checkout(new BigDecimal("500000"));

        System.out.println("-----");

        // 2. Đổi strategy lúc runtime: sang PayPal
        service.setStrategy(new PayPalPayment("a@example.com"));
        service.checkout(new BigDecimal("250000"));
        service.refund(new BigDecimal("50000"));

        System.out.println("-----");

        // 3. Ví điện tử
        service.setStrategy(new EWalletPayment("0901234567"));
        service.checkout(new BigDecimal("120000"));

        System.out.println("-----");

        // 4. Chuyển khoản: không hỗ trợ hoàn tiền
        service.setStrategy(new BankTransferPayment("0011223344"));
        service.checkout(new BigDecimal("900000"));
        try {
            service.refund(new BigDecimal("100000"));
        } catch (UnsupportedOperationException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        System.out.println("-----");

        // 5. Thêm phương thức mới KHÔNG cần sửa code cũ (OCP)
        service.setStrategy(new PaymentStrategy() {
            public void pay(BigDecimal amount) {
                System.out.println("Thanh toán bằng QR Code: " + amount);
            }
            public String getName() { return "QR_CODE"; }
        });
        service.checkout(new BigDecimal("75000"));
    }
}