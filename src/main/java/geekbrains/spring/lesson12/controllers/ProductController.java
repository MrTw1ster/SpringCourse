package geekbrains.spring.lesson12.controllers;

import geekbrains.spring.lesson12.repositories.ProductSpecifications;
import geekbrains.spring.lesson12.services.ProductService;
import geekbrains.spring.lesson12.SortDirection;
import geekbrains.spring.lesson12.exceptions.IncorrectParamException;
import geekbrains.spring.lesson12.exceptions.ProductNotFoundException;
import geekbrains.spring.lesson12.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAll(@RequestParam MultiValueMap<String, String> params,
                                   @RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size,
                                   @RequestParam(required = false) String sortCost,
                                   @RequestParam(required = false) String sortTitle,
                                   @RequestParam(required = false) Boolean costFirst) {
        validatePagingParams(page, size);
        validateSortingParams(sortCost, sortTitle, costFirst);
        try {
            Page<ProductDto> productPage = productService.getAll(ProductSpecifications.build(params), page - 1, size,
                    sortCost != null ? SortDirection.valueOf(sortCost) : null,
                    sortTitle != null ? SortDirection.valueOf(sortTitle) : null, costFirst);
            if (page > productPage.getTotalPages()) {
                throw new IncorrectParamException("The total number of pages is " + productPage.getTotalPages());
            }
            return productPage.toList();
        } catch (IllegalArgumentException e) {
            throw new IncorrectParamException(e.getMessage());
        }
    }

    public void validateSortingParams(String sortCost, String sortTitle, Boolean costFirst) {
        if (sortCost != null && sortTitle != null && costFirst == null) {
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
    public ProductDto getById(@PathVariable Integer id) {
        return productService.getById(id).orElseThrow(() -> new ProductNotFoundException("There is no product with id " + id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto add(@RequestBody ProductDto product) {
        product.setId(null);
        return productService.addOrUpdate(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ProductDto update(@RequestBody ProductDto product) {
        return productService.addOrUpdate(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }
}
