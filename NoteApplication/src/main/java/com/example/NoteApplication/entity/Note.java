package com.example.NoteApplication.entity;

import com.example.NoteApplication.entity.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name = "Notes")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class Note extends Auditable {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String content;
    @Enumerated(EnumType.STRING)
    private Subject subject;
}
