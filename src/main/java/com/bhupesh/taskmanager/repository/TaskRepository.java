package com.bhupesh.taskmanager.repository;

import com.bhupesh.taskmanager.model.Task;
import com.bhupesh.taskmanager.model.Task.TaskStatus;
import com.bhupesh.taskmanager.model.Task.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByUserIdAndStatus(Long userId, TaskStatus status);
    List<Task> findByUserIdAndPriority(Long userId, TaskPriority priority);
    long countByUserIdAndStatus(Long userId, TaskStatus status);
}
