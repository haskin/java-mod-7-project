package dev.haskin.javamod7springproject.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.haskin.javamod7springproject.dto.UserAdvanced;
import dev.haskin.javamod7springproject.model.User;
import dev.haskin.javamod7springproject.repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/test")
    UserAdvanced getTestUser() {
        User user = userRepository.findById(1L).orElse(null);
        // user.setReadingList(Set.of(ReadingList.builder().name("Adrian's Better List")
        // .books(books.stream().collect(Collectors.toSet())).build()));
        // userRepository.save(user);
        user.setReadingList(null);
        return modelMapper.map(user, UserAdvanced.class);
    }
}
