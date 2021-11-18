package com.exam.examserver.service.impl;

import com.exam.examserver.model.User;
import com.exam.examserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        User userFromDb = userRepository.findByUsername(user);
        if(userFromDb==null){
            throw new UsernameNotFoundException("User not found !");
        }
        return userFromDb;
    }
}
