package geekbrains.spring.lesson12.services;

import geekbrains.spring.lesson12.exceptions.IncorrectParamException;
import geekbrains.spring.lesson12.model.Role;
import geekbrains.spring.lesson12.model.User;
import geekbrains.spring.lesson12.repositories.RoleRepository;
import geekbrains.spring.lesson12.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user, String r) {
        Role role = roleRepository.findByName("ROLE_" + r.toUpperCase());
        if (role == null) {
            throw new IncorrectParamException("There is no role " + r);
        }
        user.addRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User saveUserRole(User user, String r) {
        Role role = roleRepository.findByName("ROLE_" + r.toUpperCase());
        if (role == null) {
            throw new IncorrectParamException("There is no role " + r);
        }
        user.addRole(role);
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}