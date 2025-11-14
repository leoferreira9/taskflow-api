package com.leonardo.taskflow.service;

import com.leonardo.taskflow.dto.TaskUpdateDTO;
import com.leonardo.taskflow.exception.EntityNotFoundException;
import com.leonardo.taskflow.model.Task;
import com.leonardo.taskflow.model.TaskPriority;
import com.leonardo.taskflow.model.TaskStatus;
import com.leonardo.taskflow.model.User;
import com.leonardo.taskflow.repository.TaskRepository;
import com.leonardo.taskflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task create(Task task){
        User user = userRepository
                .findById(task.getUser().getId()).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + task.getUser().getId()));

        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task findById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));
    }

    public List<Task> findAll(){
        List<Task> tasks = taskRepository.findAll();
        if(tasks.isEmpty()) throw new EntityNotFoundException("No tasks registered!");
        return tasks;
    }

    public Page<Task> findAllPaginated(Pageable pageable){
        return taskRepository.findAll(pageable);
    }

    public Page<Task> filterTasks(TaskStatus status, TaskPriority priority, Long userId, Pageable pageable){

        if(status != null && priority == null && userId == null) return taskRepository.findByStatus(status, pageable);
        if(status == null && priority != null && userId == null) return taskRepository.findByPriority(priority, pageable);
        if(status == null && priority == null && userId != null) return taskRepository.findByUserId(userId, pageable);

        if(status != null && priority != null && userId == null) return taskRepository.findByStatusAndPriority(status, priority, pageable);
        if(status != null && priority == null && userId != null) return taskRepository.findByStatusAndUserId(status, userId, pageable);
        if(status == null && priority != null && userId != null) return taskRepository.findByPriorityAndUserId(priority, userId, pageable);

        if(status != null && priority != null && userId != null) return taskRepository.findByStatusAndPriorityAndUserId(status, priority, userId, pageable);

        return taskRepository.findAll(pageable);
    }

    public Task update(Task task){
        Task existingTask = taskRepository.findById(task.getId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + task.getId()));

        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setTitle(task.getTitle());

        return taskRepository.save(existingTask);
    }

    public Task partialUpdate(Long id, TaskUpdateDTO dto){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));

        if(dto.getDescription() != null) existingTask.setDescription(dto.getDescription());
        if(dto.getStatus() != null) existingTask.setStatus(dto.getStatus());
        if(dto.getPriority() != null) existingTask.setPriority(dto.getPriority());
        if(dto.getDueDate() != null) existingTask.setDueDate(dto.getDueDate());
        if(dto.getTitle() != null) existingTask.setTitle(dto.getTitle());

        return taskRepository.save(existingTask);
    }

    public void delete(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));

        taskRepository.delete(task);
    }
}
