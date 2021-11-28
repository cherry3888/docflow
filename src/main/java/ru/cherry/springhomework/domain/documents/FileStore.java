package ru.cherry.springhomework.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="file_store", schema="documents")
public class FileStore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_store_seq")
    @SequenceGenerator(name = "file_store_seq", sequenceName = "file_store_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "document_id")
    private Document document;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "data")
    private byte[] data;

}
