package com.leonardo.taskflow.controller;

import com.leonardo.taskflow.dto.UserDTO;
import com.leonardo.taskflow.model.User;
import com.leonardo.taskflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id){
        User user = userService.findById(id);
        return new UserDTO(user);
    }

    @GetMapping
    public List<UserDTO> findAll(){
        List<User> users = userService.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody @Valid User user){
        user.setId(id);
        User entity = userService.update(user);
        return new UserDTO(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
