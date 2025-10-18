package br.com.rhssolutions.desafioJunior.controller;


import br.com.rhssolutions.desafioJunior.domain.enums.Priority;
import br.com.rhssolutions.desafioJunior.domain.enums.Status;
import br.com.rhssolutions.desafioJunior.dto.ApiResponse;
import br.com.rhssolutions.desafioJunior.dto.TaskDTORequest;
import br.com.rhssolutions.desafioJunior.dto.TaskDTOResponse;
import br.com.rhssolutions.desafioJunior.dto.TaskStatusUpdateDTO;
import br.com.rhssolutions.desafioJunior.service.TaskService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskDTOResponse>> create(@Valid @RequestBody TaskDTORequest taskDTO) {
        TaskDTOResponse task = taskService.createTask(taskDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                201,
                "Task created successfully",
                task,
                LocalDateTime.now()
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TaskDTOResponse>>> list(@RequestParam(required = false) Long projectId,
                                                                   @RequestParam(required = false) Priority priority,
                                                                   @RequestParam(required = false) Status status,
                                                                   @ParameterObject Pageable pageable) {
        Page<TaskDTOResponse> tasks = taskService.findAllTasks(status, priority, projectId, pageable);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Tasks listed successfully",
                tasks,
                LocalDateTime.now()
        ));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<TaskDTOResponse>> updateStatus(@PathVariable Long id, @RequestBody TaskStatusUpdateDTO status) {
        TaskDTOResponse task = taskService.updateStatusTask(id, status);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Task status updated successfully",
                task,
                LocalDateTime.now()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Task deleted successfully",
                null,
                LocalDateTime.now()
        ));

    }

}
