package ht10.repositories;

import ht10.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
    @Modifying
    @Query(value = "update sessions s set s.updated_at = now() where s.guid = :guid", nativeQuery = true)
    int updateSessionUpdatedAt(@Param("guid") UUID guid);
}
