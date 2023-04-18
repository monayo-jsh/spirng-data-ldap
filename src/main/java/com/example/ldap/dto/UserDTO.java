package com.example.ldap.dto;

import com.example.ldap.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {

    private String id;
    private String name;
    private String mobile;
    private String email;

    @JsonIgnore
    private String password;

    public static UserDTO of(User user) {
        return UserDTO.builder()
                      .id(user.getUid())
                      .name(user.getLastName() + user.getFirstName())
                      .mobile(user.getMobile())
                      .email(user.getEmail())
                      .build();
    }

}
