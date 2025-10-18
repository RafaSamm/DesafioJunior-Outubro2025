package br.com.rhssolutions.desafioJunior.service;

import br.com.rhssolutions.desafioJunior.domain.enums.Priority;
import br.com.rhssolutions.desafioJunior.domain.enums.Status;
import br.com.rhssolutions.desafioJunior.dto.TaskDTORequest;
import br.com.rhssolutions.desafioJunior.dto.TaskDTOResponse;
import br.com.rhssolutions.desafioJunior.dto.TaskStatusUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    TaskDTOResponse createTask(TaskDTORequest taskDTO);

    Page<TaskDTOResponse> findAllTasks(Status status, Priority priority, Long projectId, Pageable pageable);

    TaskDTOResponse updateStatusTask(Long id, TaskStatusUpdateDTO taskStatusUpdateDTO);

    void deleteTask(Long id);
}
