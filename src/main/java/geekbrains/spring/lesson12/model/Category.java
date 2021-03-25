package geekbrains.spring.lesson12.model;

import geekbrains.spring.lesson12.model.dto.CategoryDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "id_category"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<Product> products;

    public Category(CategoryDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }
}
