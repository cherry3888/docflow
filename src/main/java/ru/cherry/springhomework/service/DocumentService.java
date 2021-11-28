package ru.cherry.springhomework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.cherry.springhomework.domain.administration.User;
import ru.cherry.springhomework.domain.dictionaries.DocStatus;
import ru.cherry.springhomework.domain.documents.Document;

import java.util.List;

public interface DocumentService {
    List<Document> getAllDocs();
    Document getById(Long id);
    Document save(Document document, User user, MultipartFile[] files);
    List<DocStatus> getAvalableStatuses(User user, Document document);
}
