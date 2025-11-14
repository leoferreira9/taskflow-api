package com.leonardo.taskflow.service;

import com.leonardo.taskflow.dto.UserUpdateDTO;
import com.leonardo.taskflow.exception.EntityNotFoundException;
import com.leonardo.taskflow.model.User;
import com.leonardo.taskflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) throw new EntityNotFoundException("No users registered!");
        return users;
    }

    public Page<User> findAllPaginated(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public User update(User user){
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + user.getId()));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public User partialUpdate(Long id, UserUpdateDTO user){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        if(user.getName() != null) existingUser.setName(user.getName());
        if(user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if(user.getRole() != null) existingUser.setRole(user.getRole());
        if(user.getPassword() != null) existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        userRepository.delete(user);
    }
}
