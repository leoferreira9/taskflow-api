package com.leonardo.taskflow.controller;

import com.leonardo.taskflow.dto.UserDTO;
import com.leonardo.taskflow.model.User;
import com.leonardo.taskflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO create(@RequestBody @Valid User user){
        User entity =  userService.create(user);
        return new UserDTO(entity);
    }
}
