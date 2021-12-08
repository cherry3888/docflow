package ru.cherry.springhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cherry.springhomework.domain.administration.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}
