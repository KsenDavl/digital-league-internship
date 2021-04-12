package com.example.examproject.security.service.impl;

import com.example.examproject.security.dao.UserMapper;
import com.example.examproject.security.model.Role;
import com.example.examproject.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AppUserServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Autowired
    public AppUserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userMapper.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
        Set<Role> roles = userMapper.getRolesByUserid(user.getUserId());
        user.setRoles(roles);
        return user;
    }
}
