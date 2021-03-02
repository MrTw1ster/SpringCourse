package ht4;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    private List<ht4.Product> products;

    public List<ht4.Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new ht4.Product(1, "AI-100", 110));
        products.add(new ht4.Product(2, "AI-95", 100));
        products.add(new ht4.Product(3, "AI-92", 90));
        products.add(new ht4.Product(4, "AI-80", 75));
        products.add(new ht4.Product(5, "Diesel", 95));
    }

    public void add(ht4.Product product) {
        if (product.getCost() < 0) {
            throw new IllegalArgumentException("The cost must be greater than 0");
        }
        if (product.getTitle().isEmpty()) {
            throw new IllegalArgumentException("The title cannot be empty");
        }
        products.add(product);
    }

    public Optional<ht4.Product> getById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    public ArrayList<ht4.Product> getByListId(List<Integer> ids) {
        ArrayList<ht4.Product> res = new ArrayList<>();
        for (ht4.Product p : products) {
            if (ids.contains(p.getId())) {
                res.add(p);
            }
        }
        return res;
    }

    public ht4.Product updateById(int id, String newTitle) {
        for (ht4.Product p : products) {
            if (p.getId() == id) {
                p.setTitle(newTitle);
                return p;
            }
        }
        return null;
    }

    public ht4.Product updateById(int id, double newCost) {
        for (ht4.Product p : products) {
            if (p.getId() == id) {
                p.setCost(newCost);
                return p;
            }
        }
        return null;
    }

    public ht4.Product deleteById(int id) {
        for (ht4.Product p : products) {
            if (p.getId() == id) {
                products.remove(p);
                return p;
            }
        }
        return null;
    }
}
