package com.example.NoteApplication.entity;

import com.example.NoteApplication.entity.audit.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Courses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString
public class Course extends Auditable {

    @Id
    @GeneratedValue
    private long id;
    private String name;

}
