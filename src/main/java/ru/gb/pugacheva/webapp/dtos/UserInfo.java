package ru.gb.pugacheva.webapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {
    private String username;
    private String password;
    private String email;
}
