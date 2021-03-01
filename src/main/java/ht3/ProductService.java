package ht3;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public int getProductsCount() {
        return productRepository.getProducts().size();
    }

    public double getAvgCost() {
        List<Product> products = productRepository.getProducts();
        if (products.size() == 0) {
            return 0;
        }

        int avg = 0;
        for (Product p : products) {
            avg += p.getCost();
        }
        avg /= products.size();
        return avg;
    }

    public void addProduct(int id, String title, double cost) {
        productRepository.add(new Product(id, title, cost));
    }

    public void addProduct(Product product) {
        productRepository.add(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    public Product getProduct(int id) {
        return productRepository.getById(id);
    }

    public ArrayList<Product> getListProduct(List<Integer> ids) {
        return productRepository.getByListId(ids);
    }

    public Product updateTitle(int id, String newTitle) {
        return productRepository.updateById(id, newTitle);
    }

    public Product updateCost(int id, double cost) {
        return productRepository.updateById(id, cost);
    }

    public Product deleteProduct(int id) {
        return productRepository.deleteById(id);
    }
}
