package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.ProductDTO;
import guru.springfamework.api.v1.model.ProductListDTO;
import guru.springfamework.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is my Product Controller")
@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {
    public static final String BASE_URL = "/api/v1/products";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "This will get a list of all products.", notes = "These are some notes about the API")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductListDTO findAll() {
        return new ProductListDTO(productService.findAll());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO findByName(@PathVariable String name) {
        return productService.findByName(name);
    }
}
