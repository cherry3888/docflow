package ru.cherry.springhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.domain.dictionaries.DocStatus;

@Repository
public interface DocStatusRepository extends JpaRepository<DocStatus, String> {

}
