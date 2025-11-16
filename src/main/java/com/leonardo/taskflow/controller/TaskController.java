package com.leonardo.taskflow.controller;

import com.leonardo.taskflow.dto.TaskDTO;
import com.leonardo.taskflow.dto.TaskUpdateDTO;
import com.leonardo.taskflow.model.Task;
import com.leonardo.taskflow.model.TaskPriority;
import com.leonardo.taskflow.model.TaskStatus;
import com.leonardo.taskflow.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tasks")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Criar uma nova task")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@RequestBody @Valid Task task){
        Task entity = taskService.create(task);
        return new TaskDTO(entity);
    }

    @Operation(summary = "Listar tasks com paginação")
    @GetMapping("/paginated")
    public Page<TaskDTO> findAllPaginated(Pageable pageable){
        Page<Task> page = taskService.findAllPaginated(pageable);
        return page.map(TaskDTO::new);
    }

    @Operation(summary = "Listar tasks utilizando filtros")
    @GetMapping("/filtered")
    public Page<TaskDTO> findFiltered(@RequestParam(required = false) TaskStatus status,
                                      @RequestParam(required = false) TaskPriority priority,
                                      @RequestParam(required = false) Long userId,
                                      Pageable pageable){

        Page<Task> page = taskService.filterTasks(status, priority, userId, pageable);
        return page.map(TaskDTO::new);
    }

    @Operation(summary = "Buscar task pelo ID")
    @GetMapping("/{id}")
    public TaskDTO findById(@PathVariable Long id){
        Task task = taskService.findById(id);
        return new TaskDTO(task);
    }

    @Operation(summary = "Listar todas as tasks")
    @GetMapping
    public List<TaskDTO> findAll(){
        List<Task> tasks = taskService.findAll();
        return tasks.stream().map(TaskDTO::new).toList();
    }

    @Operation(summary = "Atualizar todos os dados da task")
    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable Long id, @RequestBody @Valid Task task){
        task.setId(id);
        Task entity = taskService.update(task);
        return new TaskDTO(entity);
    }

    @Operation(summary = "Atualizar dados parciais da task")
    @PatchMapping("/{id}")
    public TaskDTO partialUpdate(@PathVariable Long id, @RequestBody TaskUpdateDTO dto){
        Task task = taskService.partialUpdate(id, dto);
        return new TaskDTO(task);
    }

    @Operation(summary = "Deletar task")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        taskService.delete(id);
    }
}
