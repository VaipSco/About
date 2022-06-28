package ru.ilinov.about.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ilinov.about.service.QuestionService;

@Controller
@RequestMapping("/administration")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdministrationController {

    QuestionService questionService;

    public AdministrationController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ResponseBody
    @GetMapping("/test")
    public String testAdminController() {

        return "Test method of Administration Controller";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MODER')")
    @GetMapping("/question/moderate")
    public String moderateQuestion(@RequestParam(name="id") Long questionId, @RequestParam(name="approve") Boolean approve) {
        if (approve) {
            questionService.approveQuestion(questionId);
            return "redirect:/question/" + questionId;
        } else {
            questionService.deleteQuestion(questionId);
            return "redirect:/question";
        }
    }
}
