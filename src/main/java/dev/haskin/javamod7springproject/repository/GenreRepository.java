package dev.haskin.javamod7springproject.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.haskin.javamod7springproject.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Set<Genre> findAllByNameIn(Set<String> names);
}
