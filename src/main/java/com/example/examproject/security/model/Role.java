package com.example.examproject.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role implements GrantedAuthority {

    private int roleId;
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
