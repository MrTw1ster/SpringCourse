package ht9;

import ht9.exceptions.IncorrectParamException;
import ht9.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ht9.ProductService productService;

    @GetMapping
    public List<ht9.ProductDto> getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        validatePagingParams(page, size);
        Page<ht9.ProductDto> productPage = productService.getAll(page - 1, size);
        if (page > productPage.getTotalPages()) {
            throw new IncorrectParamException("The total number of pages is " + productPage.getTotalPages());
        }
        return productPage.toList();
    }

    @GetMapping("/sort")
    public List<Product> getAllSorted(@RequestParam(required = false) String sortCost,
                                      @RequestParam(required = false) String sortTitle,
                                      @RequestParam(required = false) Boolean costFirst) {
        validateSortingParams(sortCost, sortTitle, costFirst);
        try {
            return productService.getAllSorted(sortCost != null ? ht9.SortDirection.valueOf(sortCost) : null,
                    sortTitle != null ? ht9.SortDirection.valueOf(sortTitle) : null, costFirst);
        } catch (IllegalArgumentException e) {
            throw new IncorrectParamException(e.getMessage());
        }
    }

    public void validateSortingParams(String sortCost, String sortTitle, Boolean costFirst) {
        if (sortCost == null && sortTitle == null) {
            throw new IncorrectParamException("The sort type must be specified!");
        } else if (sortCost != null && sortTitle != null && costFirst == null) {
            throw new IncorrectParamException("Sort priority must be specified!");
        }
    }

    public void validatePagingParams(int page, int size) {
        if (page < 1) {
            throw new IncorrectParamException("Page index must not be less than one!");
        }
        if (size < 1) {
            throw new IncorrectParamException("Page size must not be less than one!");
        }
    }

    @GetMapping("/{id}")
    public ht9.ProductDto getById(@PathVariable Integer id) {
        return productService.getById(id).orElseThrow(() -> new ProductNotFoundException("There is no product with id " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ht9.ProductDto add(@RequestBody ht9.ProductDto product) {
        product.setId(null);
        return productService.addOrUpdate(product);
    }

    @PutMapping
    public ht9.ProductDto update(@RequestBody ht9.ProductDto product) {
        return productService.addOrUpdate(product);
    }

    @DeleteMapping("/{id}")
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
