package dev.haskin.javamod7springproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.haskin.javamod7springproject.model.Book;
import dev.haskin.javamod7springproject.model.ReadingList;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList, Long> {
}
