package ht7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }

    @GetMapping("/gt")
    public List<Product> getAllByMinCost(@RequestParam Double min) {
        return productService.getAllByMinCost(min);
    }

    @GetMapping("/lt")
    public List<Product> getAllByMaxCost(@RequestParam Double max) {
        return productService.getAllByMaxCost(max);
    }

    @GetMapping("/btw")
    public List<Product> getAllByMinAndMaxCost(@RequestParam Double min, @RequestParam Double max) {
        return productService.getAllByMinAndMaxCost(min, max);
    }

    @GetMapping("/title")
    public List<Product> getAllByTitle(@RequestParam String title) {
        return productService.getAllByTitle(title);
    }
}
