package com.example.ldap.repository;

import com.example.ldap.entity.User;
import java.util.Optional;
import org.springframework.data.ldap.repository.LdapRepository;

public interface UserRepository extends LdapRepository<User> {
    Optional<User> findByUid(String uid);
}
