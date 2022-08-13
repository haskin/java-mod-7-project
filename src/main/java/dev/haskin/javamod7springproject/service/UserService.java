package dev.haskin.javamod7springproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.javamod7springproject.dto.ReadingListAdvanced;
import dev.haskin.javamod7springproject.dto.ReadingListBasic;
import dev.haskin.javamod7springproject.dto.UserAdvanced;
import dev.haskin.javamod7springproject.dto.UserBasic;
import dev.haskin.javamod7springproject.model.User;
import dev.haskin.javamod7springproject.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReadingListService readingListService;

    @Autowired
    private ModelMapper modelMapper;

    public UserBasic deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.getReadingList().forEach(readingList -> readingListService.deleteById(readingList.getId()));
        userRepository.delete(user);
        return modelMapper.map(user, UserBasic.class);
    }

    public UserBasic createUser(UserAdvanced userAdvanced) {
        User user = modelMapper.map(userAdvanced, User.class);
        return modelMapper.map(userRepository.save(user), UserBasic.class);
    }

    public UserAdvanced getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        UserAdvanced userAdvanced = modelMapper.map(user, UserAdvanced.class);
        // Set<ReadingListAdvanced> readingLists =
        // userAdvanced.getReadingList().stream()
        // .map(readingList -> modelMapper.map(readingList, ReadingListAdvanced.class))
        // .collect(Collectors.toSet());
        // userAdvanced.setReadingList(readingLists);
        return userAdvanced;
    }

    public ReadingListAdvanced getReadingList(Long userId, Long readingListId) {
        UserAdvanced userAdvanced = getById(userId);
        return userAdvanced.getReadingList().stream()
                .filter(rlist -> rlist.getId().equals(readingListId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Could not find Reading List Id: " + readingListId + " for User Id: " +
                                userId));
        // // return null;
    }

    public List<ReadingListBasic> getReadingLists(Long userId) {
        UserAdvanced userAdvanced = getById(userId);
        return userAdvanced.getReadingList().stream()
                .map(readingList -> modelMapper.map(readingList, ReadingListBasic.class))
                .collect(Collectors.toList());
    }
}
