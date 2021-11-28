package ru.cherry.springhomework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import ru.cherry.springhomework.domain.administration.Role;
import ru.cherry.springhomework.domain.administration.User;
import ru.cherry.springhomework.domain.dictionaries.DocStatus;
import ru.cherry.springhomework.domain.dictionaries.DocType;
import ru.cherry.springhomework.domain.documents.Document;
import ru.cherry.springhomework.security.UserService;
import ru.cherry.springhomework.service.DictionaryService;
import ru.cherry.springhomework.service.DocumentService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentController.class)
public class DocumentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;
    @MockBean
    private DictionaryService dictionaryService;
    @MockBean
    private UserService userService;


    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    @Test
    void getAllDocumentsPositiveTest() throws Exception {

        Document document = new Document();
        document.setDocType(new DocType("NOTE", "Служебная записка", false));
        document.setDocStatus(new DocStatus("DRAFT", "Черновик", false));
        document.setDateCreated(OffsetDateTime.now());
        Role role = new Role("ADMIN", "Администратор");
        User user = new User();
        user.setUsername("admin");
        user.setLastname("Табуреткин");
        user.setName("Николай");
        user.setSurename("Григорьевич");
        user.setRoles(Set.of(role));
        document.setCreator(new User());
        List<Document> documents = List.of(document);

        given(documentService.getAllDocs()).willReturn(documents);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("documents", equalTo(documents)));
    }

}
