package ru.ilinov.about.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ilinov.about.entity.Category;
import ru.ilinov.about.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("create")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping("create")
    public String createCategory(Category category) {
        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("")
    public String categoryManagement(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/categories";
    }

    @DeleteMapping("delete/{id}")
    public String deleteCategory(Model model, @PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/category";
    }

    @GetMapping("edit/{id}")
    public String editCategory(Model model, @PathVariable Long id) {
        model.addAttribute("category", categoryService.findById(id));
        return "category/edit";
    }

    @PutMapping("edit/{id}")
    public String editCategory(Category editedCategory, @PathVariable Long id) {
        Category category = categoryService.findById(id);
        category.setName(editedCategory.getName());
        categoryService.save(category);
        return "redirect:/category";
    }
}
