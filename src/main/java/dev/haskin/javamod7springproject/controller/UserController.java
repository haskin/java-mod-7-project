package dev.haskin.javamod7springproject.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.haskin.javamod7springproject.dto.ReadingListAdvanced;
import dev.haskin.javamod7springproject.dto.UserAdvanced;
import dev.haskin.javamod7springproject.model.User;
import dev.haskin.javamod7springproject.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}/reading_lists")
    private List<ReadingListAdvanced> getReadingList(@PathVariable Long id) {
        return userService.getReadingList(id);
    }

    @GetMapping("/test")
    UserAdvanced getTestUser() {
        return null;
    }
}
