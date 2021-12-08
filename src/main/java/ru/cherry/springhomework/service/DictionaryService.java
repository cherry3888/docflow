package ru.cherry.springhomework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cherry.springhomework.dao.DocStatusRepository;
import ru.cherry.springhomework.dao.DocTypeRepository;
import ru.cherry.springhomework.domain.dictionaries.DocStatus;
import ru.cherry.springhomework.domain.dictionaries.DocType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {
    private final DocStatusRepository docStatusRepository;
    private final DocTypeRepository docTypeRepository;

    @Transactional(readOnly = true)
    public DocStatus getDocStatus(String code) {
        return docStatusRepository.findById(code).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<DocStatus> getDocStatusList() {
        return docStatusRepository.findAll();
    }

    @Transactional(readOnly = true)
    public DocType getDocType(String code) {
        return docTypeRepository.findById(code).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<DocType> getDocTypeList() {
        return docTypeRepository.findAll();
    }

}
