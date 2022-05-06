package ru.ilinov.about.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ilinov.about.service.QuestionService;

@Controller
public class MainController {

    QuestionService questionService;

    MainController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/questions")
    public String getAllQuestions(Model model) {

        model.addAttribute("quests",questionService.getAllQuestions());

        return "questions";
    }
    
}