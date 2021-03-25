package geekbrains.spring.lesson12.repositories;

import geekbrains.spring.lesson12.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}