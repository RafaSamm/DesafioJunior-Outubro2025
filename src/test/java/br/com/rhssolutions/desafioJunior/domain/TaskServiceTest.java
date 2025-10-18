package br.com.rhssolutions.desafioJunior.domain;

import br.com.rhssolutions.desafioJunior.common.TaskFactory;
import br.com.rhssolutions.desafioJunior.domain.models.Task;
import br.com.rhssolutions.desafioJunior.dto.TaskDTORequest;
import br.com.rhssolutions.desafioJunior.exception.ProjectNotFoundException;
import br.com.rhssolutions.desafioJunior.exception.TaskNotFoundException;
import br.com.rhssolutions.desafioJunior.repository.ProjectRepository;
import br.com.rhssolutions.desafioJunior.repository.TaskRepository;
import br.com.rhssolutions.desafioJunior.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    private Task task;

    @BeforeEach
    void setup() {
        task = TaskFactory.createTask();
    }


    @Test
    public void testCreateTask() {
        TaskDTORequest taskDTORequest = TaskFactory.createTaskDTO();
        when(projectRepository.findById(
                taskDTORequest.projectId())).thenReturn(
                Optional.ofNullable(TaskFactory.createTask().getProject()));

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        var sut = taskService.createTask(taskDTORequest);

        assertEquals(sut.title(), task.getTitle());
        assertNotNull(sut);

        verify(taskRepository, times(1)).save(any(Task.class));

    }

    @Test
    public void testCreateTaskInvalid() {
        when(projectRepository.findById(
                task.getProject().getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.createTask(TaskFactory.createTaskDTO()))
                .isInstanceOf(ProjectNotFoundException.class)
                .hasMessageContaining("Project not found");

        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    public void testfindAllTasks() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Task> TaskFactory = new PageImpl<>(List.of(task));

        when(taskRepository.findByStatusAndPriorityAndProjectId(
                task.getStatus(),
                task.getPriority(),
                task.getProject().getId(),
                pageable)).thenReturn(TaskFactory);

        var sut = taskService.findAllTasks(
                task.getStatus(),
                task.getPriority(),
                task.getProject().getId(), pageable);

        assertNotNull(sut);
        assertEquals(sut.getContent().getFirst().title(), task.getTitle());
        assertEquals(1, sut.getTotalElements());

        verify(taskRepository, times(1)).findByStatusAndPriorityAndProjectId(
                task.getStatus(),
                task.getPriority(),
                task.getProject().getId(),
                pageable);
    }

    @Test
    public void testfindAllTasksInvalid() {
        PageRequest pageable = PageRequest.of(0, 10);

        when(taskRepository.findByStatusAndPriorityAndProjectId(
                eq(task.getStatus()),
                eq(task.getPriority()),
                eq(1L),
                eq(pageable))).thenReturn(Page.empty());

        assertThatThrownBy(() -> taskService.findAllTasks(
                task.getStatus(),
                task.getPriority(),
                1L, pageable))
                .isInstanceOf(ProjectNotFoundException.class)
                .hasMessageContaining("Project not found");

        verify(taskRepository, times(1)).findByStatusAndPriorityAndProjectId(
                eq(task.getStatus()),
                eq(task.getPriority()),
                eq(1L),
                eq(pageable));
    }

    @Test
    public void testUpdateStatusTask() {
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        var sut = taskService.updateStatusTask(task.getId(),
                TaskFactory.createTaskStatusUpdateDTO());

        assertNotNull(sut);
        assertEquals(sut.status(), task.getStatus());

        verify(taskRepository, times(1)).findById(task.getId());
    }

    @Test
    public void testUpdateStatusTaskInvalid() {
        when(taskRepository.findById(task.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.updateStatusTask(task.getId(),
                TaskFactory.createTaskStatusUpdateDTO()))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessageContaining("Task not found");

        verify(taskRepository, times(1)).findById(task.getId());
    }

    @Test
    public void testDeleteTask() {
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    public void testDeleteTaskInvalid() {
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.deleteTask(taskId))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessageContaining("Task not found");

        verify(taskRepository, never()).delete(any(Task.class));
    }
}
