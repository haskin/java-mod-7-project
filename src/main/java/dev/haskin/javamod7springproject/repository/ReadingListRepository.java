package dev.haskin.javamod7springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.haskin.javamod7springproject.model.ReadingList;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList, Long> {

}
