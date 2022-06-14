package ru.ilinov.about.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

public enum Role implements GrantedAuthority {
    USER, ADMIN, MODER, BLOGGER;


    @Override
    public String getAuthority() {
        return name();
    }
}
