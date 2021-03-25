package geekbrains.spring.lesson12.controllers;

import geekbrains.spring.lesson12.model.dto.BasketProductDto;
import geekbrains.spring.lesson12.model.Session;
import geekbrains.spring.lesson12.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping
    public Session getSession() {
        return sessionService.getSession();
    }

    @GetMapping("/basket")
    public List<BasketProductDto> getBasket(@CookieValue("session_guid") UUID guid) {
        return sessionService.getBasket(guid);
    }

    @PostMapping("/basket")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@CookieValue("session_guid") UUID guid, @RequestBody BasketProductDto basketProduct) {
        basketProduct.setId(null);
        sessionService.add(guid, basketProduct);
    }

    @PutMapping("/basket")
    public void update(@CookieValue("session_guid") UUID guid, @RequestBody BasketProductDto basketProduct) {
        sessionService.update(guid, basketProduct);
    }

    @DeleteMapping("/basket/{id}")
    public void delete(@CookieValue("session_guid") UUID guid, @PathVariable Long id) {
        sessionService.delete(guid, id);
    }

}
