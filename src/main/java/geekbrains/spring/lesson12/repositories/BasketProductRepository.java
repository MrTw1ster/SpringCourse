package geekbrains.spring.lesson12.repositories;

import geekbrains.spring.lesson12.model.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
    @Modifying
    @Query(value = "delete from basket_products b where b.id = :id and b.session_guid = :guid", nativeQuery = true)
    int deleteByIdAndSessionId(@Param("guid") UUID guid, @Param("id") Long id);

    @Modifying
    @Query(value = "update basket_products b set b.product_count = :count where b.id = :id and b.session_guid = :guid", nativeQuery = true)
    int updateCountByIdAndGuid(@Param("guid") UUID guid, @Param("id") Long id, @Param("count") Integer count);
}
