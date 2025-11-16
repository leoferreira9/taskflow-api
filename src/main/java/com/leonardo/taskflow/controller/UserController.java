package com.leonardo.taskflow.controller;

import com.leonardo.taskflow.dto.TaskCreateDTO;
import com.leonardo.taskflow.dto.TaskDTO;
import com.leonardo.taskflow.dto.UserDTO;
import com.leonardo.taskflow.dto.UserUpdateDTO;
import com.leonardo.taskflow.model.Task;
import com.leonardo.taskflow.model.User;
import com.leonardo.taskflow.service.TaskService;
import com.leonardo.taskflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Criar um novo usuário")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid User user){
        User entity =  userService.create(user);
        return new UserDTO(entity);
    }

    @Operation(summary = "Criar uma task vinculada a um usuário")
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

    @Operation(summary = "Buscar usuário pelo ID")
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id){
        User user = userService.findById(id);
        return new UserDTO(user);
    }

    @Operation(summary = "Listar todos os usuários")
    @GetMapping
    public List<UserDTO> findAll(){
        List<User> users = userService.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    @Operation(summary = "Listar usuários com paginação")
    @GetMapping("/paginated")
    public Page<UserDTO> findAllPaginated(Pageable pageable){
        Page<User> page = userService.findAllPaginated(pageable);
        return page.map(UserDTO::new);
    }

    @Operation(summary = "Atualizar todos os dados do usuário")
    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody @Valid User user){
        user.setId(id);
        User entity = userService.update(user);
        return new UserDTO(entity);
    }

    @Operation(summary = "Atualizar dados parciais do usuário")
    @PatchMapping("/{id}")
    public UserDTO partialUpdate(@PathVariable Long id, @RequestBody UserUpdateDTO dto){
        User entity = userService.partialUpdate(id, dto);
        return new UserDTO(entity);
    }

    @Operation(summary = "Deletar usuário")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
