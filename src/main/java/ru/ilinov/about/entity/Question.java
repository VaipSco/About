package ru.ilinov.about.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

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


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private User author;

    @ManyToOne
    @JoinColumn
    private Blogger blogger;

    private Date creationDate;

    @ManyToOne
    @JoinColumn
    private Category category;

}
