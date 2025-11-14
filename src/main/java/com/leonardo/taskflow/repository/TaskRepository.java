package com.leonardo.taskflow.repository;

import com.leonardo.taskflow.model.Task;
import com.leonardo.taskflow.model.TaskPriority;
import com.leonardo.taskflow.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByStatus(TaskStatus status, Pageable pageable);
    Page<Task> findByPriority(TaskPriority priority, Pageable pageable);
    Page<Task> findByUserId(Long userId, Pageable pageable);

    Page<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority, Pageable pageable);
    Page<Task> findByStatusAndUserId(TaskStatus status, Long userId, Pageable pageable);
    Page<Task> findByPriorityAndUserId(TaskPriority priority, Long userId, Pageable pageable);

    Page<Task> findByStatusAndPriorityAndUserId(TaskStatus status, TaskPriority priority, Long userId, Pageable pageable);
}
