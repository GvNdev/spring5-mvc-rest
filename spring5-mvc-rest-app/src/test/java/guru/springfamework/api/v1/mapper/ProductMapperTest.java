package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.ProductDTO;
import guru.springfamework.domain.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductMapperTest {
    public static final long PRODUCT_ID = 1L;
    public static final String PRODUCT_NAME = "Banana";
    public static final double PRODUCT_PRICE = 1.6;

    ProductMapper productMapper = ProductMapper.INSTANCE;

    @Test
    public void productToProductDTO() {
        // Given
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setPrice(PRODUCT_PRICE);

        // When
        ProductDTO productDTO = productMapper.productToProductDTO(product);

        // Then

        assertEquals(Long.valueOf(PRODUCT_ID), productDTO.getId());
        assertEquals(PRODUCT_NAME, productDTO.getName());
        assertEquals(Double.valueOf(PRODUCT_PRICE), productDTO.getPrice());
    }
}