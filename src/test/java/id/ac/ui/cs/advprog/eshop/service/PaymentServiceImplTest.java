package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    private Order order;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testAddPaymentVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("pay-001", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, PaymentMethod.VOUCHER_CODE.getValue(), paymentData);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
    }

    @Test
    void testAddPaymentCOD() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Margonda No. 1");
        paymentData.put("deliveryFee", "5000");

        Payment payment = new Payment("pay-002", PaymentMethod.CASH_ON_DELIVERY.getValue(), order, paymentData);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), result.getStatus());
    }

    @Test
    void testSetStatusSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-001", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-001", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusInvalid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-001", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment, "MEOW"));
    }

    @Test
    void testGetPaymentFound() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-001", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        doReturn(payment).when(paymentRepository).findById("pay-001");

        Payment result = paymentService.getPayment("pay-001");

        assertEquals("pay-001", result.getId());
    }

    @Test
    void testGetPaymentNotFound() {
        doReturn(null).when(paymentRepository).findById("nonexistent");

        Payment result = paymentService.getPayment("nonexistent");

        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-001", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);

        doReturn(List.of(payment)).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();

        assertEquals(1, results.size());
    }
}