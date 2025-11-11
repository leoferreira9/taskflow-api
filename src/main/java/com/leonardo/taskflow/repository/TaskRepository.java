package com.leonardo.taskflow.repository;

import com.leonardo.taskflow.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}
