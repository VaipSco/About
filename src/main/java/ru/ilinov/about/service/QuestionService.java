package ru.ilinov.about.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.ilinov.about.entity.Answer;
import ru.ilinov.about.entity.Blogger;
import ru.ilinov.about.entity.Question;
import ru.ilinov.about.entity.User;
import ru.ilinov.about.repository.BloggerRepository;
import ru.ilinov.about.repository.QuestionRepository;
import ru.ilinov.about.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class QuestionService {

    QuestionRepository questionRepository;

    UserRepository userRepository;

    BloggerRepository bloggerRepository;

    QuestionService(QuestionRepository questionRepository, UserRepository userRepository, BloggerRepository bloggerRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.bloggerRepository = bloggerRepository;
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
        Blogger blogger = bloggerRepository.findFirstByName(question.getBlogger().getName());
        question.setBlogger(blogger);
        question.setCreationDate(new Date());
        Answer answer = question.getAnswers().get(0);
        answer.setQuestion(question);
        answer.setAuthor((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        answer.setLinkToVideo(getVideoIdFromYoutubeURI(question.getAnswers().get(0).getLinkToVideo()));
        questionRepository.save(question);
        return true;
    }


    //TODO Сделать обработку на некорректность введенного URI
    private String getVideoIdFromYoutubeURI(String youtubeVideoURI) {
        Pattern videoIdPattern = Pattern.compile("v=(\\w+)&");
        Matcher videoIdMatcher = videoIdPattern.matcher(youtubeVideoURI);
        String videoId;
        if (videoIdMatcher.find()) {
            videoId = videoIdMatcher.group(1);
            return videoId;
        } else {
            return null;
        }

    }

}
