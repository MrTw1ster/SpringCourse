package ht4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ht4.ProductService productService;

    public ProductController(ht4.ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProductById(Model model, @PathVariable Integer id) {
        model.addAttribute("product", productService.getProduct(id).orElseThrow(() -> new ht4.ProductNotFoundException(id)));
        return "product";
    }

    @ExceptionHandler(ht4.ProductNotFoundException.class)
    @ResponseBody
    public String handleException(ht4.ProductNotFoundException e) {
        return e.getMessage();
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
    public String addNewProduct(@ModelAttribute ht4.Product product) {
        productService.addProduct(product);
        return "redirect:/products/all";
    }

}
