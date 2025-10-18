package br.com.rhssolutions.desafioJunior.common;

import br.com.rhssolutions.desafioJunior.domain.models.Project;
import br.com.rhssolutions.desafioJunior.dto.ProjectDTORequest;

import java.time.LocalDate;

public class ProjectFactory {

    public static Project createEntity() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Project 1");
        project.setDescription("Description 1");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusMonths(2));
        return project;
    }

    public static ProjectDTORequest createDTO() {
        return new ProjectDTORequest("Project 1",
                "Description 1",
                LocalDate.now(),
                LocalDate.now().plusMonths(2));
    }
}
