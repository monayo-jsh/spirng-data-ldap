package com.example.ldap.entity;

import java.util.HashSet;
import java.util.Set;
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
@NoArgsConstructor
@AllArgsConstructor
@Entry(
    base = "ou=groups",
    objectClasses = { "top", "groupOfNames" }
)
public class Group {

    @Id
    private Name id;

    @Attribute(name = "cn")
    @DnAttribute(value = "cn", index=1)
    private String name;

    @Attribute(name = "member")
    private Set<Name> members = new HashSet<>();

    @Attribute(name = "description")
    private String description;

}
