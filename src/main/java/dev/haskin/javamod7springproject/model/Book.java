package dev.haskin.javamod7springproject.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Table(name = "Book")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    @Min(1)
    private int pages;
    @NotNull
    @LastModifiedDate
    @Column(columnDefinition = "DATE")
    private LocalDate published;
}
