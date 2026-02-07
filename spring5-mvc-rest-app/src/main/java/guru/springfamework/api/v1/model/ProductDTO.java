package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by jt on 9/24/17.
 */
@Data
public class ProductDTO {
    private Long id;

    @ApiModelProperty(value = "This is the name", required = true)
    private String name;

    @ApiModelProperty(value = "This is the price", required = true)
    private Double price;
}
