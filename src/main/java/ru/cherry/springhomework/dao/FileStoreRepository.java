package ru.cherry.springhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.domain.documents.Document;
import ru.cherry.springhomework.domain.documents.FileStore;

import java.util.List;

@Repository
public interface FileStoreRepository extends JpaRepository<FileStore, Long> {
    List<FileStore> findAllByDocument(Document document);
}
