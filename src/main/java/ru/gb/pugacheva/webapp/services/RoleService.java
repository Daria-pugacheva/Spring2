package ru.gb.pugacheva.webapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.pugacheva.webapp.model.Role;
import ru.gb.pugacheva.webapp.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role giveDefaultUserRole(){
        return roleRepository.getById(1L); //первой у нас в репозитории лежит роль юзера (в теории можно расширить методом поиска по имени роли)
    }

}
