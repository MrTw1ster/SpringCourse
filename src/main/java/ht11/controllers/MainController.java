package ht11.controllers;

import ht11.entities.ScoreDto;
import ht11.entities.User;
import ht11.exceptions.IncorrectParamException;
import ht11.exceptions.UserNotFoundException;
import ht11.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Profile("dao")
@Slf4j
@RequestMapping("/score")
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/inc")
    public ScoreDto increaseScore(Principal principal, @RequestParam Integer dif) {
        if (dif == null || dif <= 0) {
            throw new IncorrectParamException("Parameter dif must be specified and greater than 0");
        }
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException("unable to find user by username: " + principal.getName()));
        user.setScore(user.getScore() + dif);
        userService.updateUser(user);
        return new ScoreDto(user.getScore());
    }

    @GetMapping("/dec")
    public ScoreDto decreaseScore(Principal principal, @RequestParam Integer dif) {
        if (dif == null || dif <= 0) {
            throw new IncorrectParamException("Parameter dif must be specified and greater than 0");
        }
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException("unable to find user by username: " + principal.getName()));
        user.setScore(user.getScore() > dif ? user.getScore() - dif : 0);
        userService.updateUser(user);
        return new ScoreDto(user.getScore());
    }

    @GetMapping("/get/current")
    public ScoreDto getCurrentScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException("unable to find user by username: " + principal.getName()));
        return new ScoreDto(user.getScore());
    }

    @GetMapping("/get/{id}")
    public ScoreDto getScoreById(@PathVariable Integer id) {
        return userService.getScore(id);
    }

}
