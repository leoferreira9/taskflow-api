package com.leonardo.taskflow.service;

import com.leonardo.taskflow.exception.EntityNotFoundException;
import com.leonardo.taskflow.model.Task;
import com.leonardo.taskflow.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task create(Task task){
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

    public void delete(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));

        taskRepository.delete(task);
    }
}
