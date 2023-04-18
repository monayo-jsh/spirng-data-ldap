package com.example.ldap.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.ldap.entity.Group;
import java.util.Optional;
import javax.naming.Name;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.support.LdapNameBuilder;

@SpringBootTest
class GroupRepositoryTest {

    @Autowired
    GroupRepository groupRepository;

    @Test
    void testFindByName() {
        //given
        String groupName = "platform";

        //when
        Optional<Group> findGroup = groupRepository.findByName(groupName);

        //then
        assertThat(findGroup.isPresent()).isTrue();

        Group group = findGroup.get();

        assertThat(group.getName()).isEqualTo(groupName);
    }

    @Test
    void testAddMember() {
        //given
        String groupName = "platform";

        //when
        Optional<Group> findGroup = groupRepository.findByName(groupName);

        //then
        assertThat(findGroup.isPresent()).isTrue();

        Group group = findGroup.get();

        Name newUser = LdapNameBuilder.newInstance("dc=dev,dc=monayo,dc=com")
                                      .add("ou", "users")
                                      .add("uid", "newUser")
                                      .build();

        group.getMembers().add(newUser);

        int expectedMemberSize = group.getMembers().size();

        groupRepository.save(group);

        findGroup = groupRepository.findByName(groupName);

        assertThat(findGroup.isPresent()).isTrue();

        group = findGroup.get();

        assertThat(group.getMembers().size()).isEqualTo(expectedMemberSize);


        //delete member newUser
        group.getMembers().remove(newUser);

        groupRepository.save(group);
    }
}