package com.example.ldap.restful.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.ldap.dto.UserDTO;
import com.example.ldap.entity.User;
import com.example.ldap.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testFindAll() throws Exception {
        //given
        MockHttpServletRequestBuilder request = request(HttpMethod.GET, "/users");

        //when
        ResultActions resultActions = mockMvc.perform(request)
                                             .andDo(print());

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void testFindUser() throws Exception {
        //given
        String uid = "monayo";
        MockHttpServletRequestBuilder request = request(HttpMethod.GET, "/users/"+uid);

        //when
        ResultActions resultActions = mockMvc.perform(request)
                                             .andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                     .andExpect(jsonPath("$.id").value(uid))
                     .andExpect(jsonPath("$.name").hasJsonPath())
                     .andExpect(jsonPath("$.mobile").hasJsonPath())
                     .andExpect(jsonPath("$.email").hasJsonPath());
    }

    @Test
    void testCreateUser() throws Exception {
        //given
        UserDTO userDTO = UserDTO.builder()
                                 .id("create")
                                 .name("주상현")
                                 .email("create@gmail.com")
                                 .mobile("010-1234-1234")
                                 .build();

        String requestBody = objectMapper.writeValueAsString(userDTO);

        MockHttpServletRequestBuilder request = request(HttpMethod.POST, "/users")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON);

        //when
        ResultActions resultActions = mockMvc.perform(request)
                                             .andDo(print());

        //then
        resultActions.andExpect(status().isOk());

        //delete user
        userRepository.delete(User.builder().uid(userDTO.getId()).build());
    }
}