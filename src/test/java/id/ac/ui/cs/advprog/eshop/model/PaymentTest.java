package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Order order;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testCreatePaymentVoucherCodeSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("pay-001", "VOUCHER_CODE", order, paymentData);

        assertEquals("pay-001", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentVoucherCodeRejectedNot16Chars() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123ABC");

        Payment payment = new Payment("pay-002", "VOUCHER_CODE", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherCodeRejectedNotStartWithESHOP() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "XSHOP1234ABC5678");

        Payment payment = new Payment("pay-003", "VOUCHER_CODE", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherCodeRejectedNot8Digits() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPABCDEFGHIJK"); // no digits

        Payment payment = new Payment("pay-004", "VOUCHER_CODE", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Margonda Raya No. 100");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("pay-005", "CASH_ON_DELIVERY", order, paymentData);

        assertEquals("WAITING_PAYMENT", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedEmptyAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("pay-006", "CASH_ON_DELIVERY", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedNullAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", null);
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("pay-007", "CASH_ON_DELIVERY", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedEmptyDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Margonda Raya No. 100");
        paymentData.put("deliveryFee", "");

        Payment payment = new Payment("pay-008", "CASH_ON_DELIVERY", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedNullDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Margonda Raya No. 100");
        paymentData.put("deliveryFee", null);

        Payment payment = new Payment("pay-009", "CASH_ON_DELIVERY", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
}