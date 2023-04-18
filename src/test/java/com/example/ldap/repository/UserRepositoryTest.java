package com.example.ldap.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.ldap.entity.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testFindAll() {
        //given

        //when
        List<User> users = userRepository.findAll();

        //then
        assertThat(users).isNotEmpty();
    }

    @Test
    void testFindByUid() {
        //given
        String uid = "monayo";

        //when
        Optional<User> findUser = userRepository.findByUid(uid);

        //then
        assertThat(findUser.isPresent()).isTrue();

        User user = findUser.get();

        assertThat(user.getUid()).isEqualTo(uid);
    }

    @Test
    void testCreateUser() {
        //given
        User createUser = User.builder()
                              .uid("create")
                              .displayName("create")
                              .firstName("상현")
                              .lastName("주")
                              .commonName("상현 주")
                              .email("create@gmail.com")
                              .mobile("010-1234-1234")
                              .password("password")
                              .build();

        //when
        userRepository.save(createUser);

        Optional<User> findUser = userRepository.findByUid(createUser.getUid());

        assertThat(findUser.isPresent()).isTrue();

        User savedUser = findUser.get();

        assertThat(savedUser.getId()).isEqualTo(createUser.getId());

        //delete user
        userRepository.delete(createUser);
    }

}