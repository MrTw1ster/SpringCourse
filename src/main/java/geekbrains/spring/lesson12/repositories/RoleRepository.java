package geekbrains.spring.lesson12.repositories;

import geekbrains.spring.lesson12.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}