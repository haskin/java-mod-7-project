package dev.haskin.javamod7springproject.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.javamod7springproject.dto.ReadingListAdvanced;
import dev.haskin.javamod7springproject.dto.ReadingListBasic;
import dev.haskin.javamod7springproject.dto.UserAdvanced;
import dev.haskin.javamod7springproject.dto.UserBasic;
import dev.haskin.javamod7springproject.model.Book;
import dev.haskin.javamod7springproject.model.ReadingList;
import dev.haskin.javamod7springproject.model.User;
import dev.haskin.javamod7springproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReadingListService readingListService;

    @Autowired
    private BookService bookService;

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

    /**
     * Creartes a Reading List. It DOES NOT create Books. Can only add books
     * to the reading list already in the database.
     * 
     * @param readingListAdvanced
     * @return
     */
    @Transactional
    public ReadingListBasic createReadingList(Long userId, @Valid ReadingListAdvanced readingListAdvanced) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Set<Long> bookIds = readingListAdvanced.getBooks().stream().map(book -> book.getId())
                .collect(Collectors.toSet());
        List<Book> books = bookService.getAllBooksFromIds(bookIds);
        if (books.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid books have been requested");
        log.debug("Books: {}", books);

        log.info("Adding Reading List to Books & User");
        ReadingList readingList = modelMapper.map(readingListAdvanced, ReadingList.class);
        readingList = readingListService.createReadingList(readingList);
        user.getReadingList().add(readingList);
        readingList.getBooks().addAll(books);
        for (Book book : books) {
            book.getReadingList().add(readingList);
        }
        return modelMapper.map(readingList, ReadingListBasic.class);
    }
}
