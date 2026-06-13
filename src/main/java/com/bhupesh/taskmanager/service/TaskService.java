package com.bhupesh.taskmanager.service;

import com.bhupesh.taskmanager.dto.TaskDTO;
import com.bhupesh.taskmanager.model.Task;
import com.bhupesh.taskmanager.model.Task.TaskStatus;
import com.bhupesh.taskmanager.model.Task.TaskPriority;
import com.bhupesh.taskmanager.model.User;
import com.bhupesh.taskmanager.repository.TaskRepository;
import com.bhupesh.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Convert Entity → DTO
    private TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUserId(task.getUser().getId());
        return dto;
    }

    public List<TaskDTO> getTasksByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return taskRepository.findByUserId(user.getId())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TaskDTO createTask(TaskDTO dto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus() != null ? dto.getStatus() : TaskStatus.TODO);
        task.setPriority(dto.getPriority() != null ? dto.getPriority() : TaskPriority.MEDIUM);
        task.setDueDate(dto.getDueDate());
        task.setUser(user);

        return toDTO(taskRepository.save(task));
    }

    public TaskDTO updateTask(Long taskId, TaskDTO dto, String email) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Security check: only the owner can update their task
        if (!task.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());

        return toDTO(taskRepository.save(task));
    }

    public void deleteTask(Long taskId, String email) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        taskRepository.delete(task);
    }

    public TaskDTO updateStatus(Long taskId, TaskStatus status, String email) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        task.setStatus(status);
        return toDTO(taskRepository.save(task));
    }
}
