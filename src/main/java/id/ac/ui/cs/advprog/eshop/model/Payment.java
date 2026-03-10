package id.ac.ui.cs.advprog.eshop.model;

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
        if (method.equals("VOUCHER_CODE")) {
            return validateVoucherCode(paymentData.get("voucherCode"));
        } else if (method.equals("CASH_ON_DELIVERY")) {
            return validateCOD(paymentData);
        }
        return "WAITING_PAYMENT";
    }

    private String validateVoucherCode(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16) {
            return "REJECTED";
        }
        if (!voucherCode.startsWith("ESHOP")) {
            return "REJECTED";
        }
        long digitCount = voucherCode.chars()
                .filter(Character::isDigit)
                .count();
        if (digitCount != 8) {
            return "REJECTED";
        }
        return "SUCCESS";
    }

    private String validateCOD(Map<String, String> paymentData) {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");
        if (address == null || address.isEmpty() ||
                deliveryFee == null || deliveryFee.isEmpty()) {
            return "REJECTED";
        }
        return "WAITING_PAYMENT";
    }
}