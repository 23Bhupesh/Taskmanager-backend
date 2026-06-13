package com.bhupesh.taskmanager.controller;

import com.bhupesh.taskmanager.dto.TaskDTO;
import com.bhupesh.taskmanager.model.Task.TaskStatus;
import com.bhupesh.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // GET /api/tasks — Get all tasks for logged-in user
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(taskService.getTasksByUser(userDetails.getUsername()));
    }

    // POST /api/tasks — Create a new task
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO dto,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(taskService.createTask(dto, userDetails.getUsername()));
    }

    // PUT /api/tasks/{id} — Update a task
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id,
                                               @RequestBody TaskDTO dto,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(taskService.updateTask(id, dto, userDetails.getUsername()));
    }

    // PATCH /api/tasks/{id}/status — Update just the status
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskDTO> updateStatus(@PathVariable Long id,
                                                 @RequestParam TaskStatus status,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(taskService.updateStatus(id, status, userDetails.getUsername()));
    }

    // DELETE /api/tasks/{id} — Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        taskService.deleteTask(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
