package ht8;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCostGreaterThan (Double minCost);
    List<Product> findByCostLessThan (Double maxCost);
    List<Product> findByCostBetween (Double minCost, Double maxCost);
    List<Product> findByTitleIgnoreCaseContaining (String title);
}
