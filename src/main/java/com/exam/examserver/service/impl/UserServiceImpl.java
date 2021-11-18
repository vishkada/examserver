package com.exam.examserver.service.impl;

import com.exam.examserver.exceptions.InvalidUserException;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.repository.RoleRepository;
import com.exam.examserver.repository.UserRepository;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {

        User dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser!=null){
            throw new InvalidUserException("User already present with given username. Please try with different username!");
        } else{
            for(UserRole userRole: userRoles){
                roleRepository.save(userRole.getRole());
            }
            user.setUserRoles(userRoles);
            return userRepository.save(user);
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public User getUserByUserId(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUserByUserId(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user){
        return userRepository.save(user);
    }

}
