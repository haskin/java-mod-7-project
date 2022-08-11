package dev.haskin.javamod7springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.haskin.javamod7springproject.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
