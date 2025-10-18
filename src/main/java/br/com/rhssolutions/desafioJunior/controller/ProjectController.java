package br.com.rhssolutions.desafioJunior.controller;

import br.com.rhssolutions.desafioJunior.dto.ApiResponse;
import br.com.rhssolutions.desafioJunior.dto.ProjectDTORequest;
import br.com.rhssolutions.desafioJunior.dto.ProjectDTOResponse;
import br.com.rhssolutions.desafioJunior.service.ProjectService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectDTOResponse>> create(@Valid @RequestBody ProjectDTORequest projectDTO) {
        ProjectDTOResponse project = projectService.create(projectDTO);

        ApiResponse<ProjectDTOResponse> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Project created successfully",
                project,
                LocalDateTime.now()

        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> list(@ParameterObject Pageable pageable) {
        Object data;
        // Se o pageable for vazio, retorna lista normal
        if (pageable.isUnpaged()) {
            data = projectService.listAllNonPageable();
        } else {
            data = projectService.listAll(pageable);
        }
        ApiResponse<?> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Projects listed successfully",
                data,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }


}
