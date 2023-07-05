package com.practice.studenthub.service;

import com.practice.studenthub.entity.User;

public interface UserService {
    User getUser(Long id);

    User getUser(String username);

    User saveUser(User user);
}
