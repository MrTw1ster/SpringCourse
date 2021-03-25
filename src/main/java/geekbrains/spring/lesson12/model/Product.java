package geekbrains.spring.lesson12.model;

import geekbrains.spring.lesson12.model.dto.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String title;
    @Column
    private Double cost;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<Category> categories;

    public Product(ProductDto productDto) {
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.cost = productDto.getCost();
        this.categories = productDto.getCategories() == null ? null : productDto.getCategories().stream().map(Category::new).collect(Collectors.toList());
    }
}
