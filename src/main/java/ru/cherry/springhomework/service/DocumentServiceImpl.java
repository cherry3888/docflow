package ru.cherry.springhomework.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.cherry.springhomework.dao.AvailableStatusRepository;
import ru.cherry.springhomework.dao.DocumentRepository;
import ru.cherry.springhomework.domain.administration.AvailableStatus;
import ru.cherry.springhomework.domain.administration.Role;
import ru.cherry.springhomework.domain.administration.User;
import ru.cherry.springhomework.domain.dictionaries.DocStatus;
import ru.cherry.springhomework.domain.documents.Document;
import ru.cherry.springhomework.domain.documents.FileStore;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DictionaryService dictionaryService;
    private final AvailableStatusRepository availableStatusRepository;
    private final FileStoreService fileStoreService;

    @HystrixCommand(commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")},
            fallbackMethod = "getEmptyDocList")
    @Transactional(readOnly = true)
    @Override
    public List<Document> getAllDocs() {
        List<Document> documents = documentRepository.findAll();
        return documents;
    }

    @Transactional(readOnly = true)
    @Override
    public Document getById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Document save(Document document, User user, MultipartFile[] files) {
        List<FileStore> fileStoreList = new ArrayList<FileStore>();

        if (null == document.getId()) {
            document.setDateCreated(OffsetDateTime.now());
            document.setCreator(user);
            document.setDocStatus(dictionaryService.getDocStatus("DRAFT"));
        } else {
            Document prevDocument = documentRepository.findById(document.getId()).orElse(null);
            if (null != prevDocument) {
                document.setDateCreated(prevDocument.getDateCreated());
                document.setCreator(prevDocument.getCreator());
                if (null == document.getDocStatus()) {
                    //Если нет досупных статусов, оставляем на прежнем.
                    document.setDocStatus(prevDocument.getDocStatus());
                }
                if (null == document.getDocType()) {
                    document.setDocType(prevDocument.getDocType());
                }
                if (!CollectionUtils.isEmpty(prevDocument.getFileStoreList())) {
                    fileStoreList = prevDocument.getFileStoreList();
                }
            }
        }
        document.setDateChanged(OffsetDateTime.now());
        log.info("User " + user.getUsername() + " saved document " + document);

        try {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    FileStore fileStore = new FileStore();
                    fileStore.setDocument(document);
                    fileStore.setFileName(file.getOriginalFilename());
                    fileStore.setData(file.getBytes());
                    fileStoreList.add(fileStore);
                    log.info("Save file - " + file.getOriginalFilename());
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        document.setFileStoreList(fileStoreList);

        return documentRepository.save(document);
    }

    @Override
    public List<DocStatus> getAvalableStatuses(User user, Document document) {
        List<Role> roles = new ArrayList<>(user.getRoles());
        List<AvailableStatus> availableStatusList = availableStatusRepository.findAllByRoleInAndDocTypeAndDocStatus(roles, document.getDocType(), document.getDocStatus());
        log.debug("User " + user.getUsername() + ". Available statuses: " + availableStatusList);

        return availableStatusList.stream()
                .map(AvailableStatus::getDocNextStatus)
                .collect(Collectors.toList());
    }

    private List<Document> getEmptyDocList() {
        return Collections.emptyList();
    }

}
