package dev.haskin.javamod7springproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import dev.haskin.javamod7springproject.model.User;
import dev.haskin.javamod7springproject.repository.UserRepository;

public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    UserService userService;

    private User user;

    @BeforeEach
    void setup() {
        user = User.builder().id(1L).build();
    }

    @Test
    void gets_a_user() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        // assertEquals(user, userService.getById(user.getId());
    }
}
