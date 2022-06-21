package ru.ilinov.about.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ilinov.about.entity.Role;
import ru.ilinov.about.entity.User;
import ru.ilinov.about.repository.UserRepository;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //TODO Сделать проверку корректности введенных данных
    public boolean createUser(User user) {
        String userName = user.getUsername();
        if (userRepository.findByUsername(userName) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        userRepository.save(user);
        log.info("Создание пользователя: {}", userName);
        return true;
    }

}
