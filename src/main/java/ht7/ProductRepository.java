package ht7;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ht7.Product, Integer> {

    List<ht7.Product> findByCostGreaterThan (Double minCost);
    List<ht7.Product> findByCostLessThan (Double maxCost);
    List<ht7.Product> findByCostBetween (Double minCost, Double maxCost);
    List<ht7.Product> findByTitleIgnoreCaseContaining (String title);
}
