package geekbrains.spring.lesson12.model;

import geekbrains.spring.lesson12.model.dto.BasketProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "basket_products")
@Data
@NoArgsConstructor
public class BasketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_guid")
    private Session session;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_count")
    private Integer productCount;

    public BasketProduct(BasketProductDto dto) {
        this.id = dto.getId();
        this.product = new Product(dto.getProduct());
        this.productCount = dto.getProductCount();
    }
}
