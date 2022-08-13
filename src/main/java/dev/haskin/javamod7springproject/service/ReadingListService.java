package dev.haskin.javamod7springproject.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.haskin.javamod7springproject.model.Book;
import dev.haskin.javamod7springproject.model.ReadingList;
import dev.haskin.javamod7springproject.repository.ReadingListRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReadingListService {
    @Autowired
    private ReadingListRepository readingListRepository;

    @Transactional
    void deleteById(Long id) {
        ReadingList readingList = readingListRepository.getReferenceById(id);
        log.info("Deleting Reading List from all possible Books");
        Set<Book> books = readingList.getBooks();
        for (Book book : books) {
            Set<ReadingList> bookReadingList = book.getReadingList();
            bookReadingList.remove(readingList);
            book.setReadingList(bookReadingList);
        }
        readingListRepository.deleteById(id);
    }
}
