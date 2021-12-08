package ru.cherry.springhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cherry.springhomework.domain.administration.User;


public interface UserRepository extends JpaRepository<User, String> {

}
