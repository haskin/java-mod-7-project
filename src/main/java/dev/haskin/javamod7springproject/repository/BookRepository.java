package dev.haskin.javamod7springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.haskin.javamod7springproject.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
