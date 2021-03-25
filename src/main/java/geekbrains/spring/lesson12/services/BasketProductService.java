package geekbrains.spring.lesson12.services;

import geekbrains.spring.lesson12.model.BasketProduct;
import geekbrains.spring.lesson12.model.dto.BasketProductDto;
import geekbrains.spring.lesson12.repositories.BasketProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BasketProductService {
    @Autowired
    private BasketProductRepository basketProductRepository;

    public void add(BasketProduct basketProduct){
        basketProductRepository.save(basketProduct);
    }

    @Transactional
    public int updateCountByIdAndGuid(UUID guid, BasketProductDto basketProduct) {
        return basketProductRepository.updateCountByIdAndGuid(guid, basketProduct.getId(), basketProduct.getProductCount());
    }

    @Transactional
    public int deleteByIdAndGuid(UUID guid, Long id) {
        return basketProductRepository.deleteByIdAndSessionId(guid, id);
    }
}
