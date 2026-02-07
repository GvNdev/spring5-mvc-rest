package guru.springfamework.services.impl;

import guru.springfamework.api.v1.mapper.ProductMapper;
import guru.springfamework.api.v1.model.ProductDTO;
import guru.springfamework.repositories.ProductRepository;
import guru.springfamework.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findByName(String name) {
        return productMapper.productToProductDTO(productRepository.findByName(name));
    }
}
