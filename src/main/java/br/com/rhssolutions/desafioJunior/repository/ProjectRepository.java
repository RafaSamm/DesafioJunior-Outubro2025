package br.com.rhssolutions.desafioJunior.repository;

import br.com.rhssolutions.desafioJunior.domain.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Boolean existsByName(String name);
}
