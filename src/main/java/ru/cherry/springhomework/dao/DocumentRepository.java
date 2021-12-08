package ru.cherry.springhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.domain.documents.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
