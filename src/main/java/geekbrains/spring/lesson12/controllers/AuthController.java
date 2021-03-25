package geekbrains.spring.lesson12.controllers;

import geekbrains.spring.lesson12.configurations.jwt.JwtProvider;
import geekbrains.spring.lesson12.model.User;
import geekbrains.spring.lesson12.model.dto.AddRoleRequestDto;
import geekbrains.spring.lesson12.model.dto.AuthRequestDto;
import geekbrains.spring.lesson12.model.dto.AuthResponseDto;
import geekbrains.spring.lesson12.model.dto.SignUpRequestDto;
import geekbrains.spring.lesson12.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody SignUpRequestDto req) {
        User user = new User();
        user.setPassword(req.getPassword());
        user.setLogin(req.getLogin());
        user.setRoles(new ArrayList<>());
        userService.saveUser(user, "USER");
        return "OK";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add_role")
    public String addRole(@RequestBody AddRoleRequestDto req) {
        User user = userService.findByLogin(req.getLogin());
        userService.saveUserRole(user, req.getRole());
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponseDto auth(@RequestBody AuthRequestDto req) {
        User user = userService.findByLoginAndPassword(req.getLogin(), req.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponseDto(token);
    }
}