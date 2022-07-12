package ru.ilinov.about.service;

import org.springframework.stereotype.Service;
import ru.ilinov.about.entity.Category;
import ru.ilinov.about.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {return  categoryRepository.findById(id).orElse(null);}

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public void save(Category category) {categoryRepository.save(category);}
}
