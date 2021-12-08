package ru.cherry.springhomework.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cherry.springhomework.domain.administration.User;
import ru.cherry.springhomework.domain.dictionaries.DocStatus;
import ru.cherry.springhomework.domain.dictionaries.DocType;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document", schema = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_seq")
    @SequenceGenerator(name = "document_seq", sequenceName = "document_seq", allocationSize = 1)
    private Long id;

    @Column(name = "doc_number")
    private String docNumber;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "doc_type_code")
    private DocType docType;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "status_code")
    private DocStatus docStatus;

    @Column(name = "date_created")
    private OffsetDateTime dateCreated;

    @Column(name = "date_changed")
    private OffsetDateTime dateChanged;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "creator_username")
    private User creator;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "dispatcher_username")
    private User dispatcher;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "sighting_username")
    private User sighting;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "approver_username")
    private User approver;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FileStore> fileStoreList;

}
