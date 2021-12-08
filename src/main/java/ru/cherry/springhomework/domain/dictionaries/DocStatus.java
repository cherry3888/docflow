package ru.cherry.springhomework.domain.dictionaries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doc_status", schema = "dictionaries")
public class DocStatus implements Dictionary{

    @Id
    @Column(name = "code")
    private String code;

    @Column (name = "name")
    private String name;

    @Column (name = "deleted")
    private Boolean deleted;
}
