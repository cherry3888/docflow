package ru.cherry.springhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.domain.dictionaries.DocType;

@Repository
public interface DocTypeRepository extends JpaRepository<DocType, String> {

}
