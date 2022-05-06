package ru.ilinov.about.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilinov.about.entity.Question;
import ru.ilinov.about.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    QuestionRepository questionRepository;

    QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

}
