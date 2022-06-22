package ru.ilinov.about.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilinov.about.entity.Blogger;
import ru.ilinov.about.entity.Question;

public interface BloggerRepository extends JpaRepository<Blogger, Long> {
    Blogger findFirstByName(String name);
}
