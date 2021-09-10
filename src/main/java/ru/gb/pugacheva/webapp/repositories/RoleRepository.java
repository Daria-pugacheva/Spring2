package ru.gb.pugacheva.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.pugacheva.webapp.model.Role;
import ru.gb.pugacheva.webapp.model.User;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {

}
