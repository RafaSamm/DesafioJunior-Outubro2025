package br.com.rhssolutions.desafioJunior.service.impl;


import br.com.rhssolutions.desafioJunior.domain.enums.Priority;
import br.com.rhssolutions.desafioJunior.domain.enums.Status;
import br.com.rhssolutions.desafioJunior.domain.models.Project;
import br.com.rhssolutions.desafioJunior.domain.models.Task;
import br.com.rhssolutions.desafioJunior.dto.TaskDTORequest;
import br.com.rhssolutions.desafioJunior.dto.TaskDTOResponse;
import br.com.rhssolutions.desafioJunior.dto.TaskStatusUpdateDTO;
import br.com.rhssolutions.desafioJunior.exception.ProjectNotFoundException;
import br.com.rhssolutions.desafioJunior.exception.TaskNotFoundException;
import br.com.rhssolutions.desafioJunior.repository.ProjectRepository;
import br.com.rhssolutions.desafioJunior.repository.TaskRepository;
import br.com.rhssolutions.desafioJunior.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;


    @Override
    public TaskDTOResponse createTask(TaskDTORequest taskDTO) {
        Project project = projectRepository.findById(
                taskDTO.projectId()).orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        Task task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        task.setProject(project);

        Task savedTask = taskRepository.save(task);

        return new TaskDTOResponse(savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getPriority(),
                savedTask.getDueDate());

    }


    @Override
    public Page<TaskDTOResponse> findAllTasks(Status status, Priority priority, Long projectId, Pageable pageable) {
        Page<Task> tasks = taskRepository.findByStatusAndPriorityAndProjectId(status, priority, projectId, pageable);

        if (tasks.isEmpty()) {
            throw new ProjectNotFoundException("Project not found");
        }
        return tasks.map(task -> new TaskDTOResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate()));

    }

    @Override
    public TaskDTOResponse updateStatusTask(Long id, TaskStatusUpdateDTO taskStatusUpdateDTO) {
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new TaskNotFoundException("Task not found"));
        task.setStatus(taskStatusUpdateDTO.status());
        taskRepository.save(task);

        return new TaskDTOResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate());

    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new TaskNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
}
