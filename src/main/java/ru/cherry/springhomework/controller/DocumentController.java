package ru.cherry.springhomework.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.cherry.springhomework.domain.administration.User;
import ru.cherry.springhomework.domain.documents.Document;
import ru.cherry.springhomework.service.DictionaryService;
import ru.cherry.springhomework.service.DocumentService;

import java.util.List;

@Controller
public class DocumentController {
    private final DocumentService documentService;
    private final DictionaryService dictionaryService;

    public DocumentController(DocumentService documentService, DictionaryService dictionaryService) {
        this.documentService = documentService;
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/")
    public String getAllDocs(Model model, @AuthenticationPrincipal User user) {
        List<Document> documents = documentService.getAllDocs();
        model.addAttribute("documents", documents);
        return "documents";
    }

    @GetMapping("/adddoc")
    public String addNewBook(Document document, Model model, @AuthenticationPrincipal User user) {
        document.setDocStatus(dictionaryService.getDocStatus("DRAFT"));
        document.setDocType(dictionaryService.getDocType("NOTE"));
        model.addAttribute("doctypes", dictionaryService.getDocTypeList());
        model.addAttribute("availableStatuses", documentService.getAvalableStatuses(user, document));
        return "edit_doc";
    }

    @PostMapping("/savedoc")
    public String saveDocument(Document document, @AuthenticationPrincipal User user, @RequestParam("files") MultipartFile[] files) {
        documentService.save(document, user, files);
        return "redirect:/";
    }

    @GetMapping("/viewdoc")
    public String viewBook(@RequestParam("id") Long id, Model model, @AuthenticationPrincipal User user) {
        Document document = documentService.getById(id);
        if (null != document) {
            model.addAttribute("document", document);
            return "view_doc";
        }
        return "redirect:/";
    }

    @GetMapping("/editdoc")
    public String editDocument(@RequestParam("id") Long id, Model model, @AuthenticationPrincipal User user) {
        Document document = documentService.getById(id);
        if (null != document) {
            model.addAttribute("document", document);
            model.addAttribute("doctypes", dictionaryService.getDocTypeList());
            model.addAttribute("availableStatuses", documentService.getAvalableStatuses(user, document));
            return "edit_doc";
        }
        return "redirect:/";
    }

}
