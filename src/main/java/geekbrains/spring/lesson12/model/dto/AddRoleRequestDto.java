package geekbrains.spring.lesson12.model.dto;

import lombok.Data;

@Data
public class AddRoleRequestDto {
    private String login;
    private String role;
}
