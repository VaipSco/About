package ru.ilinov.about.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String description;

    @Column
    private boolean isApproved;

    @ManyToOne
    @JoinColumn
    private User authorId;

    @ManyToOne
    @JoinColumn
    private User bloggerId;

    private Date creationDate;

}
