package com.example.ldap.restful.services;

import com.example.ldap.dto.UserDTO;
import com.example.ldap.entity.User;
import com.example.ldap.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                             .stream()
                             .map(UserDTO::of)
                             .collect(Collectors.toList());
    }

    public UserDTO findUser(String uid) {
        Optional<User> user = userRepository.findByUid(uid);

        if(user.isEmpty()) return UserDTO.builder().build();

        return UserDTO.of(user.get());
    }

    public String createUser(UserDTO reqUserDTO) {
        Optional<User> findUser = userRepository.findByUid(reqUserDTO.getId());

        if (findUser.isPresent()) {
            return "user is already exists";
        }

        User user = User.builder()
                        .uid(reqUserDTO.getId())
                        .password(reqUserDTO.getId())
                        .displayName(reqUserDTO.getId())
                        .mobile(reqUserDTO.getMobile())
                        .email(reqUserDTO.getEmail())
                        .firstName(reqUserDTO.getName().substring(1))
                        .lastName(reqUserDTO.getName().substring(0, 1))
                        .commonName(String.format("%s %s",
                                                  reqUserDTO.getName().substring(1),
                                                  reqUserDTO.getName().substring(0, 1)))
                        .build();

        userRepository.save(user);

        return "user create success";
    }
}
