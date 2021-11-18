package com.exam.examserver.service;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;

import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles);

    public User updateUser(User user);

    public User getUserByUserName(String userName);

    public User getUserByUserId(Long id);

    public void deleteUserByUserId(Long id);

}
