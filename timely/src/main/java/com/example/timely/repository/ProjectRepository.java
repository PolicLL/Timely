package com.example.timely.repository;

import com.example.timely.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll();

    @Override
    Optional<Project> findById(Long aLong);

    @Query(nativeQuery = true, value = "(SELECT MAX(id) FROM project)")
    Long getIdOfLastProject();

    @Query(nativeQuery = true, value = "SELECT is_done FROM project WHERE id = (SELECT MAX(id) FROM project)")
    Boolean isLastProjectFinished();

    @Query(nativeQuery = true, value = "SELECT * FROM project WHERE id =:projectId")
    Project getProject(Long projectId);

    @Query(nativeQuery = true, value = "SELECT * FROM project WHERE id = (SELECT MAX(id) FROM project)")
    Project getLastProject();
}
