package ht3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("allProducts", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/remove/{id}")
    public String deleteProductById(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products/all";
    }

    @PostMapping("/add")
//    public String addNewProduct(@RequestParam Integer id, @RequestParam String title, @RequestParam Double cost) {
    public String addNewProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products/all";
    }

}
