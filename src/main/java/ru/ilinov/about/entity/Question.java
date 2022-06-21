package ru.ilinov.about.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private boolean isApproved;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();

    @ManyToOne
    @JoinColumn
    private User author;

    @ManyToOne
    @JoinColumn
    private User blogger;

    private Date creationDate;

}
