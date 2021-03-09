package ht8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ht8.ProductRepository productRepository;

    public ht8.Product getById(Integer id) {
        return productRepository.findById(id).get();
    }

    public Page<ht8.Product> getAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public List<ht8.Product> getAllSorted(ht8.SortDirection sortCost, ht8.SortDirection sortTitle, Boolean costFirst) {
        Sort sortByCost = null;
        Sort sortByTitle = null;
        if (sortCost != null) {
            if (sortCost == ht8.SortDirection.ASC) {
                sortByCost = Sort.by("cost");
            } else {
                sortByCost = Sort.by("cost").descending();
            }
        }

        if (sortTitle != null) {
            if (sortTitle == ht8.SortDirection.ASC) {
                sortByTitle = Sort.by("title");
            } else {
                sortByTitle = Sort.by("title").descending();
            }
        }

        if (sortByCost == null) {
            return productRepository.findAll(sortByTitle);
        } else if (sortByTitle == null) {
            return productRepository.findAll(sortByCost);
        } else {
            if (costFirst) {
                return productRepository.findAll(sortByCost.and(sortByTitle));
            } else {
                return productRepository.findAll(sortByTitle.and(sortByCost));
            }
        }
    }

    public ht8.Product add(ht8.Product product) {
        return productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public List<ht8.Product> getAllByMinCost(Double minCost) {
        return productRepository.findByCostGreaterThan(minCost);
    }

    public List<ht8.Product> getAllByMaxCost(Double maxCost) {
        return productRepository.findByCostLessThan(maxCost);
    }

    public List<ht8.Product> getAllByMinAndMaxCost(Double minCost, Double maxCost) {
        return productRepository.findByCostBetween(minCost, maxCost);
    }

    public List<ht8.Product> getAllByTitle(String title) {
        return productRepository.findByTitleIgnoreCaseContaining(title);
    }
}
