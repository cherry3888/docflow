package ru.cherry.springhomework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cherry.springhomework.dao.FileStoreRepository;
import ru.cherry.springhomework.domain.documents.FileStore;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileStoreService {
    private final FileStoreRepository fileStoreRepository;

    @Transactional(readOnly = true)
    public FileStore getFileStoreById(Long id) {
        return fileStoreRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean deleteFileById(Long id) {
        FileStore fileStore = fileStoreRepository.findById(id).orElse(null);
        if (null != fileStore) {
            fileStoreRepository.delete(fileStore);
            return true;
        }
        return false;
    }

}
