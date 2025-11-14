package com.leonardo.taskflow.controller;

import com.leonardo.taskflow.dto.TaskCreateDTO;
import com.leonardo.taskflow.dto.TaskDTO;
import com.leonardo.taskflow.dto.UserDTO;
import com.leonardo.taskflow.dto.UserUpdateDTO;
import com.leonardo.taskflow.model.Task;
import com.leonardo.taskflow.model.User;
import com.leonardo.taskflow.service.TaskService;
import com.leonardo.taskflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid User user){
        User entity =  userService.create(user);
        return new UserDTO(entity);
    }

    @PostMapping("/{id}/tasks")
    public TaskDTO createTaskForUser(@PathVariable Long id, @RequestBody TaskCreateDTO taskCreateDTO){
        User user = userService.findById(id);
        Task task = new Task();

        task.setTitle(taskCreateDTO.getTitle());
        task.setDueDate(taskCreateDTO.getDueDate());
        task.setPriority(taskCreateDTO.getPriority());
        task.setStatus(taskCreateDTO.getStatus());
        task.setDescription(taskCreateDTO.getDescription());
        task.setUser(user);

        Task savedTask = taskService.create(task);
        return new TaskDTO(savedTask);
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

    @GetMapping("/paginated")
    public Page<UserDTO> findAllPaginated(Pageable pageable){
        Page<User> page = userService.findAllPaginated(pageable);
        return page.map(UserDTO::new);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody @Valid User user){
        user.setId(id);
        User entity = userService.update(user);
        return new UserDTO(entity);
    }

    @PatchMapping("/{id}")
    public UserDTO partialUpdate(@PathVariable Long id, @RequestBody UserUpdateDTO dto){
        User entity = userService.partialUpdate(id, dto);
        return new UserDTO(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
