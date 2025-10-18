package br.com.rhssolutions.desafioJunior.domain;

import br.com.rhssolutions.desafioJunior.common.ProjectFactory;
import br.com.rhssolutions.desafioJunior.domain.models.Project;
import br.com.rhssolutions.desafioJunior.dto.ProjectDTORequest;
import br.com.rhssolutions.desafioJunior.exception.ProjectNameExistsException;
import br.com.rhssolutions.desafioJunior.exception.ProjectNotFoundException;
import br.com.rhssolutions.desafioJunior.repository.ProjectRepository;
import br.com.rhssolutions.desafioJunior.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    private Project project;

    @BeforeEach
    void setup() {
        project = ProjectFactory.createEntity();
    }

    @Test
    public void testCreateProjectValid() {
        //Arrange
        ProjectDTORequest projectDTORequest = ProjectFactory.createDTO();


        when(projectRepository.save(any(Project.class))).thenReturn(project);

        //Act
        var sut = projectService.create(projectDTORequest);

        //Assert
        assertEquals(projectDTORequest.name(), project.getName());
        assertNotNull(sut);

        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    public void testCreateProjectInvalid() {
        ProjectDTORequest projectDTORequest = ProjectFactory.createDTO();

        when(projectRepository.existsByName(projectDTORequest.name())).thenReturn(true);

        assertThatThrownBy(() -> projectService.create(projectDTORequest))
                .isInstanceOf(ProjectNameExistsException.class)
                .hasMessage("Project already exists with this name");

        verify(projectRepository, never()).save(any(Project.class));//verifico que o banco nunca foi chamado
    }

    @Test
    public void testListAllProjectsPaged() {
        PageRequest pageRequest = PageRequest.of(0, 10); // Exemplo de paginação
        Page<Project> page = new PageImpl<>(List.of(project));

        when(projectRepository.findAll()).thenReturn(List.of(project));
        when(projectRepository.findAll(pageRequest)).thenReturn(page);

        var sut1 = projectService.listAll(pageRequest);

        assertNotNull(sut1);
        assertEquals(1, sut1.getTotalElements());
        assertEquals(project.getName(), sut1.getContent().getFirst().name());

        verify(projectRepository, times(1)).findAll(pageRequest);

    }

    @Test
    public void testListAllProjectsNonPageable() {
        when(projectRepository.findAll()).thenReturn(List.of(project));

        var sut = projectService.listAllNonPageable();

        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals(project.getName(), sut.getFirst().name());

        verify(projectRepository, times(2)).findAll();
    }


    @Test
    public void testListAllProjectsPagedNotFound() {
        PageRequest pageRequest = PageRequest.of(0, 10);

        when(projectRepository.findAll()).thenReturn(Collections.emptyList());


        assertThatThrownBy(() -> projectService.listAll(pageRequest))
                .isInstanceOf(ProjectNotFoundException.class)
                .hasMessage("No projects found");

        verify(projectRepository, times(1)).findAll(); //verificar se há projetos
        verify(projectRepository, never()).findAll(pageRequest); // não chega a chamar paginação

    }
    @Test
    public void testListAllProjectsNonPageableNotFound() {
        when(projectRepository.findAll()).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> projectService.listAllNonPageable())
                .isInstanceOf(ProjectNotFoundException.class)
                .hasMessage("No projects found");

        verify(projectRepository, times(1)).findAll(); //verificar se há projetos
    }


}
