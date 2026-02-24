package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreateProduct() {
        Product result = productService.create(product);

        verify(productRepository).create(product);
        assertEquals(product, result);
    }

    @Test
    void testFindAllProducts() {
        Iterator<Product> mockIterator = List.of(product).iterator();
        when(productRepository.findAll()).thenReturn(mockIterator);

        List<Product> result = productService.findAll();

        verify(productRepository).findAll();
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }
}