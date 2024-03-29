package ht10.services;

import ht10.exceptions.ProductNotFoundException;
import ht10.exceptions.SessionExpiredException;
import ht10.model.BasketProduct;
import ht10.model.BasketProductDto;
import ht10.model.Session;
import ht10.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private BasketProductService basketProductService;

    public Session getSession() {
        return sessionRepository.save(new Session());
    }

    public List<BasketProductDto> getBasket(UUID guid) {
        try {
            return sessionRepository.findById(guid).get().getBasketProducts().stream()
                    .map(BasketProductDto::new)
                    .collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            throw new SessionExpiredException("Session was expired");
        }
    }

    @Transactional
    public void add(UUID guid, BasketProductDto basketProductDto) {
        Session s = sessionRepository.findById(guid).orElseThrow(() -> new SessionExpiredException("Session was expired"));
        BasketProduct b = new BasketProduct(basketProductDto);
        b.setSession(s);
        basketProductService.add(b);
        sessionRepository.updateSessionUpdatedAt(guid);
    }

    @Transactional
    public void delete(UUID guid, Long id) {
        if (basketProductService.deleteByIdAndGuid(guid, id) > 0) {
            sessionRepository.updateSessionUpdatedAt(guid);
        } else {
            throw new ProductNotFoundException("There is no product with id " + id + " in the basket");
        }
    }

    @Transactional
    public void update(UUID guid, BasketProductDto basketProduct) {
        if (basketProductService.updateCountByIdAndGuid(guid, basketProduct) > 0) {
            sessionRepository.updateSessionUpdatedAt(guid);
        } else {
            throw new ProductNotFoundException("There is no product with id " + basketProduct.getId() + " in the basket");
        }
    }
}
