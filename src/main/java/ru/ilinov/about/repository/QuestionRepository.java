package ru.ilinov.about.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilinov.about.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
