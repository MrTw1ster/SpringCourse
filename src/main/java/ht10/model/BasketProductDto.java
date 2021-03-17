package ht10.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BasketProductDto {
    private Long id;
    private ProductDto product;
    private Integer productCount;

    public BasketProductDto(BasketProduct basketProduct) {
        this.id = basketProduct.getId();
        this.product = new ProductDto(basketProduct.getProduct());
        this.productCount = basketProduct.getProductCount();
    }
}
