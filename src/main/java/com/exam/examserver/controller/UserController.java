package com.exam.examserver.controller;

import com.exam.examserver.model.Role;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        Role role = Role.builder().roleId(45L).roleName("Student").build();
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = UserRole.builder().role(role).user(user).build();
        userRoles.add(userRole);
        return new ResponseEntity<>(userService.createUser(user, userRoles), HttpStatus.OK);
    }

    @GetMapping("/getUserByUserName/{userName}")
    public User getUerByUserName(@PathVariable String userName){
        return userService.getUserByUserName(userName);
    }

    @GetMapping("/getUserById/{id}")
    public User getUerByUserName(@PathVariable Long id){
        return userService.getUserByUserId(id);
    }

    @DeleteMapping("/deleteUserByUserId/{id}")
    public void deleteUerByUserName(@PathVariable Long id){
        userService.deleteUserByUserId(id);
    }

    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
