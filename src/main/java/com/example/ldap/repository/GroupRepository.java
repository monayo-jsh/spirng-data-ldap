package com.example.ldap.repository;

import com.example.ldap.entity.Group;
import java.util.Optional;
import org.springframework.data.ldap.repository.LdapRepository;

public interface GroupRepository extends LdapRepository<Group> {
    Optional<Group> findByName(String name);
}
