package com.example.ldap.entity;

import javax.naming.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entry(
    base = "ou=users",
    objectClasses = { "top", "inetOrgPerson" }
)
public class User {

    @Id
    private Name id;

    @DnAttribute(value = "uid", index = 1)
    private String uid;

    @Attribute(name = "givenName")
    private String firstName;

    @Attribute(name = "sn")
    private String lastName;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "userPassword")
    private String password;

    @Attribute(name = "mobile")
    private String mobile;

    @Attribute(name = "mail")
    private String email;

    @Attribute(name = "displayName")
    private String displayName;

}
