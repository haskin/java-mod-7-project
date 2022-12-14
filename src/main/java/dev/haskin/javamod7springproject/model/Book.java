package dev.haskin.javamod7springproject.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Book")
@Entity
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Book {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @NotNull
    @Min(1)
    private int pages;

    @NotNull
    @LastModifiedDate
    private LocalDate published;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    public Author author;

    @Size(min = 1)
    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @Builder.Default
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "book_readinglist", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
            @JoinColumn(name = "readinglist_id") })
    private Set<ReadingList> readingList = new HashSet<>();
}
