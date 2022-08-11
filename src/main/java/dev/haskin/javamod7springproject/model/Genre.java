package dev.haskin.javamod7springproject.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "Genre")
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Genre {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Book> books;
}
