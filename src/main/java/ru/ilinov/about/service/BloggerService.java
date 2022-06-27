package ru.ilinov.about.service;

import org.springframework.stereotype.Service;
import ru.ilinov.about.entity.Blogger;
import ru.ilinov.about.repository.BloggerRepository;

import java.util.List;

@Service
public class BloggerService {

    BloggerRepository bloggerRepository;

    public BloggerService(BloggerRepository bloggerRepository) {
        this.bloggerRepository = bloggerRepository;
    }

    public Blogger findFirstByName(String name) {
        return bloggerRepository.findFirstByName(name);
    }

    public List<Blogger> findAllBloggers() {
        return bloggerRepository.findAll();
    }

}
