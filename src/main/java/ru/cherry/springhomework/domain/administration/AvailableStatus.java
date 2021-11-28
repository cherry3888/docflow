package ru.cherry.springhomework.domain.administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cherry.springhomework.domain.dictionaries.Dictionary;
import ru.cherry.springhomework.domain.dictionaries.DocStatus;
import ru.cherry.springhomework.domain.dictionaries.DocType;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "available_status", schema = "administration")
public class AvailableStatus implements Dictionary {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "role")
    private Role role;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "doc_type_code")
    private DocType docType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "status_code")
    private DocStatus docStatus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "next_status_code")
    private DocStatus docNextStatus;

}
