package br.com.rhssolutions.desafioJunior.common;

import br.com.rhssolutions.desafioJunior.domain.enums.Priority;
import br.com.rhssolutions.desafioJunior.domain.enums.Status;
import br.com.rhssolutions.desafioJunior.domain.models.Task;
import br.com.rhssolutions.desafioJunior.dto.TaskDTORequest;
import br.com.rhssolutions.desafioJunior.dto.TaskStatusUpdateDTO;

import java.time.LocalDate;

public class TaskFactory {

    public static Task createTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Implementar endpoint de listagem");
        task.setDescription("Criar endpoint GET /projects com paginação");
        task.setStatus(Status.TODO);
        task.setPriority(Priority.HIGH);
        task.setDueDate(LocalDate.now().plusDays(5));
        task.setProject(ProjectFactory.createEntity());
        return task;
    }

    public static TaskDTORequest createTaskDTO() {
        return new TaskDTORequest("Implementar endpoint de listagem",
                "Criar endpoint GET /projects com paginação",
                Status.TODO,
                Priority.HIGH,
                LocalDate.now().plusDays(5),
                1L);
    }

    public static TaskStatusUpdateDTO createTaskStatusUpdateDTO() {
        return new TaskStatusUpdateDTO(Status.TODO);
    }
}
