package ru.cherry.springhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.domain.administration.AvailableStatus;
import ru.cherry.springhomework.domain.administration.Role;
import ru.cherry.springhomework.domain.dictionaries.DocStatus;
import ru.cherry.springhomework.domain.dictionaries.DocType;

import java.util.List;

@Repository
public interface AvailableStatusRepository extends JpaRepository<AvailableStatus, Long> {

    List<AvailableStatus> findAllByRoleInAndDocTypeAndDocStatus(List<Role> roles, DocType docType, DocStatus docStatus);

}

