package ru.ilinov.about.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.ilinov.about.entity.Question;
import ru.ilinov.about.entity.User;
import ru.ilinov.about.repository.QuestionRepository;
import ru.ilinov.about.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    QuestionRepository questionRepository;

    UserRepository userRepository;

    QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> findQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    //TODO Сделать более грамотно
    public boolean createQuestion(Question question) {
        question.setAuthor((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User blogger = userRepository.findByUsername(question.getBlogger().getUsername());
        question.setBlogger(blogger);
        question.setCreationDate(new Date());
        questionRepository.save(question);
        return true;
    }

}
