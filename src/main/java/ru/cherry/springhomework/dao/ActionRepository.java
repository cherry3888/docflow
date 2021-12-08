package ru.cherry.springhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.domain.administration.Action;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

}
