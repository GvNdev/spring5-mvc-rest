package guru.springfamework.services;

import guru.springfamework.api.v1.model.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findByName(String name);
}
