package dev.haskin.javamod7springproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Table(name = "ReadingList")
@Entity
@Data
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    private String name;
}
