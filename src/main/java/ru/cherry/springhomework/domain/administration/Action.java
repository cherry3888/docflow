package ru.cherry.springhomework.domain.administration;

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
@Table(name = "action", schema = "administration")
public class Action {

    @Id
    @Column(name = "code")
    private String code;

    @Column (name = "name")
    private String name;

}
