package geekbrains.spring.lesson12.model.dto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String login;
    private String password;
}