package com.leonardo.taskflow.repository;

import com.leonardo.taskflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
