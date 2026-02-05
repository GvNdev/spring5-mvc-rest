package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.ProductDTO;
import guru.springfamework.api.v1.model.ProductListDTO;
import guru.springfamework.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductListDTO> findAll() {
        return new ResponseEntity<ProductListDTO>(
                new ProductListDTO(productService.findAll()), HttpStatus.OK
        );
    }

    @GetMapping("{name}")
    public ResponseEntity<ProductDTO> findByName(@PathVariable String name) {
        return new ResponseEntity<ProductDTO>(
                productService.findByName(name), HttpStatus.OK
        );
    }
}
