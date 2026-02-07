package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.ProductDTO;
import guru.springfamework.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(source = "id", target = "id")
    ProductDTO productToProductDTO(Product product);
}
