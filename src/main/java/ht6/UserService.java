package ht6;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Product> getUserProducts(int id) {
        return userRepository.getUserProducts(id);
    }

    public List<UserProducts> getUserProductsWithCurrentCost(int id) {
        return userRepository.getUserProductsWithCurrentCost(id);
    }
}
