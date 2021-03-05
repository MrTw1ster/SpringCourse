package ht6;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<User> getProductUsers(int id) {
        return productRepository.getProductUsers(id);
    }
}
