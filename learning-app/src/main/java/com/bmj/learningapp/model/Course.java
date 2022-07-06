package com.bmj.learningapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import java.time.Instant;

@Indexed
@Entity
@Table(name = "course")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Course {

    public Course() {
        this.createdAt = Instant.now();
    }

    public Course(String name, String hours) {
        this.name = name;
        this.hours = hours;
        this.createdAt = Instant.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FullTextField()
    @NaturalId()
    private String name;

    @FullTextField()
    @NaturalId()
    private String hours;

    private Instant createdAt ;
}