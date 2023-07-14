package com.unit.testing.registration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unit.testing.registration.entity.User;
import com.unit.testing.registration.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@DisplayName("User controller:")
class UserControllerTest {

    private final List<User> userList = new ArrayList<>();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private User user1;
    private User user2;

    static String getJsonObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(object);
        return requestJson;
    }

    @BeforeEach
    void setUp() {
        user1 = new User(1, "u1", 20, "bhavnagar", "gujarat");
        user2 = new User(1, "u2", 23, "ahmedabad", "gujarat");
        userList.add(user1);
        userList.add(user2);
    }

    @AfterEach
    void tearDown() {
        userList.clear();
    }

    @Test
    @DisplayName("pass case : get all users")
    void userController_testGetAllUsers_serviceGetAllUsersMethod_pass() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        this.mockMvc.perform(get("/v1/user")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    @DisplayName("fail case: get all users")
    void userController_testGetAllUsers_serviceGetAllUsersMethod_fail() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        this.mockMvc.perform(get("/v1/user/")).andDo(print()).andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("pass case: save user")
    void userController_testSaveUser_serviceAddUserMethod_pass() throws Exception {
        String jsonObj = getJsonObject(user1);
        when(userService.addUser(user1)).thenReturn(user1);
        this.mockMvc.perform(post("/v1/user").contentType(MediaType.APPLICATION_JSON).content(jsonObj)).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("fail case: save user")
    void userController_testSaveUser_serviceAddUserMethod_fail() throws Exception {
        String jsonObj = getJsonObject(user1);
        when(userService.addUser(user1)).thenReturn(null);
        this.mockMvc.perform(post("/v1/user").contentType(MediaType.APPLICATION_JSON).content(jsonObj)).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void userController_deleteUser_serviceDeleteUserById_pass() throws Exception {
        doAnswer(Answers.CALLS_REAL_METHODS).when(userService).deleteUserById(1);
        mockMvc.perform(delete("/v1/user/1")).andDo(print()).andExpect(status().isOk());
    }
}