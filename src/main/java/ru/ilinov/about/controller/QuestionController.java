package ru.ilinov.about.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ilinov.about.entity.Answer;
import ru.ilinov.about.entity.Question;
import ru.ilinov.about.entity.Role;
import ru.ilinov.about.entity.User;
import ru.ilinov.about.service.AnswerService;
import ru.ilinov.about.service.BloggerService;
import ru.ilinov.about.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    QuestionService questionService;

    AnswerService answerService;

    BloggerService bloggerService;

    QuestionController(QuestionService questionService, AnswerService answerService, BloggerService bloggerService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.bloggerService = bloggerService;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @GetMapping("/create")
    public String createQuestion(Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("bloggers", bloggerService.findAllBloggers());
        return "create_question";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @PostMapping("/create")
    public String createQuestion(Question question,
                                 @RequestParam(name = "videoStartPosition") String videoStart,
                                 @RequestParam(name = "videoEndPosition") String videoEnd) {
        Answer answer = question.getAnswers().get(0);
        answer.setVideoStartPosition(answerService.checkAndConvertTimeToAbsolute(videoStart));
        if (!videoEnd.isEmpty())
            answer.setVideoEndPosition(answerService.checkAndConvertTimeToAbsolute(videoEnd));
        questionService.createQuestion(question);
        return "redirect:/question/" + question.getId();
    }

    @GetMapping
    public String getAllQuestions(Model model) {
        model.addAttribute("questions", questionService.findApprovedQuestions());
        User user;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user.getRoles().contains(Role.ADMIN) || user.getRoles().contains(Role.MODER)) {
                List<Question> unmoderatedQuestionsList;
                unmoderatedQuestionsList = questionService.findUnmoderatedQuestions();
                model.addAttribute("unmoderatedQuestions", unmoderatedQuestionsList);
            }
        }
        return "questions";
    }

    @GetMapping("/{id}")
    public String getQuestion(@PathVariable String id, Model model) {
        User user = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        if (questionService.findQuestionById(Long.parseLong(id)).isPresent()){
            Question question = questionService.findQuestionById(Long.parseLong(id)).get();
            if (question.isApproved() || (user != null && (user.getRoles().contains(Role.ADMIN) || user.getRoles().contains(Role.MODER)))) {
                model.addAttribute("question", question);
                return "question";
            } else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Вопрос на модерации");

        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Вопрос не найден");

    }

}
