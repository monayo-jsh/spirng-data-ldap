package com.example.ldap.restful.controllers;

import com.example.ldap.dto.UserDTO;
import com.example.ldap.restful.services.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public List<UserDTO> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("{uid}")
    public UserDTO getOneUser(@PathVariable("uid") String uid) {
        return userService.findUser(uid);
    }

    @PostMapping("")
    public String createUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }
}
