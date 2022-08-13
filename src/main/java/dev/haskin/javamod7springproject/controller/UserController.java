package dev.haskin.javamod7springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.haskin.javamod7springproject.dto.ReadingListAdvanced;
import dev.haskin.javamod7springproject.dto.ReadingListBasic;
import dev.haskin.javamod7springproject.dto.UserAdvanced;
import dev.haskin.javamod7springproject.dto.UserBasic;
import dev.haskin.javamod7springproject.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserAdvanced getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/{id}/reading_lists/{list_id}")
    public ReadingListAdvanced getReadingList(@PathVariable Long id, @PathVariable(name = "list_id") Long listId) {
        return userService.getReadingList(id, listId);
    }

    @GetMapping("/{id}/reading_lists")
    public List<ReadingListBasic> getReadingLists(@PathVariable Long id) {
        return userService.getReadingLists(id);
    }

    /* Provides fuctionality to create a user with reading list */
    @PostMapping
    public UserBasic createUser(@RequestBody UserAdvanced user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public UserBasic deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/test")
    UserAdvanced getTestUser() {
        return null;
    }
}
