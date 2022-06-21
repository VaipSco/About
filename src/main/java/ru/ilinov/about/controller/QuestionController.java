package ru.ilinov.about.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ilinov.about.entity.Question;
import ru.ilinov.about.service.QuestionService;

import java.util.Optional;

@Controller
@RequestMapping("/question")
public class QuestionController {

    QuestionService questionService;

    QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @GetMapping("/create")
    public String createQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "create_question";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @PostMapping("/create")
    public String createQuestion(Question question) {

        questionService.createQuestion(question);
        return "create_question";
    }

    @GetMapping
    public String getAllQuestions(Model model) {
        model.addAttribute("quests",questionService.findAllQuestions());
        return "questions";
    }

    @GetMapping("/{id}")
    public String getQuestion(@PathVariable String id, Model model) {
        if (questionService.findQuestionById(Long.parseLong(id)).isPresent()) {
            Question question = questionService.findQuestionById(Long.parseLong(id)).get();
            model.addAttribute("question", question);
            return "question";
            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Вопрос не найден");
            }


    }

}
