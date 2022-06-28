package ru.ilinov.about.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilinov.about.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByIsApprovedIsFalse();
    List<Question> findByIsApprovedIsTrue();

}
