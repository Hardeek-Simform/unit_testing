package com.unit.testing.registration.service.impl;

import com.unit.testing.registration.entity.User;
import com.unit.testing.registration.repository.UserRepository;
import com.unit.testing.registration.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("User Service:")
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;
    private AutoCloseable autoCloseable;
    private User user;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        user = new User();
        user.setAge(23);
        user.setCity("city");
        user.setName("name");
        user.setState("State");
        userService = new UserServiceImpl(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @DisplayName("case:Get All Users")
    void testGetAllUsers() {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(
                new ArrayList<>(Collections.singleton(user))
        );
        assertThat(userService.getAllUsers().get(0).getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("case : adding user")
    void testAddUser() {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.addUser(user)).isEqualTo(user);
    }


    @Test
    @DisplayName("case: deleting user by id")
    void testDeleteVendorById() {
        mock(User.class);
        mock(UserRepository.class, CALLS_REAL_METHODS);
        doAnswer(CALLS_REAL_METHODS).when(userRepository).deleteById(1);
        userService.deleteUserById(1);
        verify(userRepository).deleteById(1);
    }
}