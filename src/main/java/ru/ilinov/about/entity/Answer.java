package ru.ilinov.about.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Question question;

    @ManyToOne
    @JoinColumn
    private User author;

    private String text;

    private String linkToVideo;

    @Column(nullable = false)
    private Integer videoStartPosition;

    private Integer videoEndPosition;


}
