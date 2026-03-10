package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Order order;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.order = order;
        this.paymentData = paymentData;
        this.status = determineStatus(method, paymentData);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String determineStatus(String method, Map<String, String> paymentData) {
        if (method.equals(PaymentMethod.VOUCHER_CODE.getValue())) {
            return validateVoucherCode(paymentData.get("voucherCode"));
        } else if (method.equals(PaymentMethod.CASH_ON_DELIVERY.getValue())) {
            return validateCOD(paymentData);
        }
        return PaymentStatus.WAITING_PAYMENT.getValue();
    }

    private String validateVoucherCode(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16) {
            return PaymentStatus.REJECTED.getValue();
        }
        if (!voucherCode.startsWith("ESHOP")) {
            return PaymentStatus.REJECTED.getValue();
        }
        long digitCount = voucherCode.chars()
                .filter(Character::isDigit)
                .count();
        if (digitCount != 8) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.SUCCESS.getValue();
    }

    private String validateCOD(Map<String, String> paymentData) {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");
        if (address == null || address.isEmpty() ||
                deliveryFee == null || deliveryFee.isEmpty()) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.WAITING_PAYMENT.getValue();
    }
}