package br.com.rhssolutions.desafioJunior.repository;

import br.com.rhssolutions.desafioJunior.domain.enums.Priority;
import br.com.rhssolutions.desafioJunior.domain.enums.Status;
import br.com.rhssolutions.desafioJunior.domain.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByStatusAndPriorityAndProjectId(
            Status status, Priority priority, Long projectId, Pageable pageable);
}
