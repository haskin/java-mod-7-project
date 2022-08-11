package dev.haskin.javamod7springproject.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "ReadingList")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class ReadingList {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    private String name;
    @ManyToMany
    @JoinTable(name = "readingList_book", joinColumns = { @JoinColumn(name = "readinglist_id") }, inverseJoinColumns = {
            @JoinColumn(name = "book_id") })
    private Set<Book> books;
}
