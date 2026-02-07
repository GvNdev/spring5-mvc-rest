package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.ProductMapper;
import guru.springfamework.api.v1.model.ProductDTO;
import guru.springfamework.domain.Product;
import guru.springfamework.repositories.ProductRepository;
import guru.springfamework.services.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    public static final Long PRODUCT_ID = 1L;
    public static final String PRODUCT_NAME = "Test Product";

    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(ProductMapper.INSTANCE,  productRepository);
    }

    @Test
    public void findAll() {
        // Given
        List<Product> products = Arrays.asList(new  Product(), new  Product(),  new  Product());
        when(productRepository.findAll()).thenReturn(products);

        // When
        List<ProductDTO> productDTOS = productService.findAll();

        // Then
        assertEquals(3, productDTOS.size());
    }

    @Test
    public void findByName() {
        // Given
        Product product = new  Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        when(productRepository.findByName(anyString())).thenReturn(product);

        // When
        ProductDTO productDTO = productService.findByName(PRODUCT_NAME);

        // Then
        assertEquals(PRODUCT_ID, productDTO.getId());
        assertEquals(PRODUCT_NAME, productDTO.getName());
    }
}