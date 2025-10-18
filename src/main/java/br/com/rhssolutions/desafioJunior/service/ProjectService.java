package br.com.rhssolutions.desafioJunior.service;

import br.com.rhssolutions.desafioJunior.dto.ProjectDTORequest;
import br.com.rhssolutions.desafioJunior.dto.ProjectDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    ProjectDTOResponse create(ProjectDTORequest project);

    Page<ProjectDTOResponse> listAll(Pageable pageable);

    List<ProjectDTOResponse> listAllNonPageable();
}
