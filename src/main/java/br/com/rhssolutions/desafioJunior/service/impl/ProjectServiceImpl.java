package br.com.rhssolutions.desafioJunior.service.impl;

import br.com.rhssolutions.desafioJunior.domain.models.Project;
import br.com.rhssolutions.desafioJunior.dto.ProjectDTORequest;
import br.com.rhssolutions.desafioJunior.dto.ProjectDTOResponse;
import br.com.rhssolutions.desafioJunior.dto.TaskDTOResponse;
import br.com.rhssolutions.desafioJunior.exception.ProjectNameExistsException;
import br.com.rhssolutions.desafioJunior.exception.ProjectNotFoundException;
import br.com.rhssolutions.desafioJunior.repository.ProjectRepository;
import br.com.rhssolutions.desafioJunior.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectDTOResponse create(ProjectDTORequest projectDTORequest) {
        // Verifica se já existe um projeto com o mesmo nome
        if (projectRepository.existsByName(projectDTORequest.name())) {
            throw new ProjectNameExistsException("Project already exists with this name");
        }

        Project project = new Project();
        BeanUtils.copyProperties(projectDTORequest, project);
        Project saved = projectRepository.save(project);

        return toResponse(saved);


    }

    @Override
    public Page<ProjectDTOResponse> listAll(Pageable pageable) {
        if (projectRepository.findAll().isEmpty()) {
            throw new ProjectNotFoundException("No projects found");
        }
        return projectRepository.findAll(pageable).map(this::toResponse);
    }

    @Override
    public List<ProjectDTOResponse> listAllNonPageable() { //Menores quantidades
        if (projectRepository.findAll().isEmpty()) {
            throw new ProjectNotFoundException("No projects found");
        }
        return projectRepository.findAll().stream().map(this::toResponse).toList();
    }


    private ProjectDTOResponse toResponse(Project project) { //Método utilitário para respostas serializadas
        List<TaskDTOResponse> tasks = project.getTasks().stream()
                .map(task -> new TaskDTOResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus(),
                        task.getPriority(),
                        task.getDueDate()
                ))
                .toList();
        return new ProjectDTOResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                tasks
        );
    }


}

