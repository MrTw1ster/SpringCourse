package ht7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getById(Integer id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllByMinCost(Double minCost) {
        return productRepository.findByCostGreaterThan(minCost);
    }

    public List<Product> getAllByMaxCost(Double maxCost) {
        return productRepository.findByCostLessThan(maxCost);
    }

    public List<Product> getAllByMinAndMaxCost(Double minCost, Double maxCost) {
        return productRepository.findByCostBetween(minCost, maxCost);
    }

    public List<Product> getAllByTitle(String title) {
        return productRepository.findByTitleIgnoreCaseContaining(title);
    }
}
