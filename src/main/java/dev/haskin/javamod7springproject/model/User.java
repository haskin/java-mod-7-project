package dev.haskin.javamod7springproject.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Table(name = "\"User\"")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private Set<ReadingList> readingList;
}
