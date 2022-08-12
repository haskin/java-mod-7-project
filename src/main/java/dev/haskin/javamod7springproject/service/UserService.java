package dev.haskin.javamod7springproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.javamod7springproject.dto.ReadingListAdvanced;
import dev.haskin.javamod7springproject.dto.UserAdvanced;
import dev.haskin.javamod7springproject.model.User;
import dev.haskin.javamod7springproject.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserAdvanced getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return modelMapper.map(user, UserAdvanced.class);
    }

    public List<ReadingListAdvanced> getReadingList(Long userId) {
        UserAdvanced userAdvanced = getById(userId);
        return userAdvanced.getReadingList().stream()
                .map(readingList -> modelMapper.map(readingList, ReadingListAdvanced.class))
                .collect(Collectors.toList());
    }
}
