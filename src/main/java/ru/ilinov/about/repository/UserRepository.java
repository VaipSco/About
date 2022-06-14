package ru.ilinov.about.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilinov.about.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
