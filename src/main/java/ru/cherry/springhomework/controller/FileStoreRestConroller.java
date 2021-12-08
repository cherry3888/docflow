package ru.cherry.springhomework.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.cherry.springhomework.domain.documents.FileStore;
import ru.cherry.springhomework.service.FileStoreService;

@Controller
public class FileStoreRestConroller {
    private final FileStoreService fileStoreService;

    public FileStoreRestConroller(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        FileStore fileStore = fileStoreService.getFileStoreById(id);
        if (null != fileStore) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileStore.getFileName() + "\"")
                    .body(fileStore.getData());
        }
        return new ResponseEntity ("File Not Found", HttpStatus.OK);
    }

    @GetMapping("/files/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        if (fileStoreService.deleteFileById(id)) {
            return new ResponseEntity ("File Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity ("File Not Found", HttpStatus.OK);
        }
    }

}
