package com.jrp.pma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jrp.pma.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project , Integer>{
	@Override
	 List<Project> findAll();
	
	@Query(nativeQuery = true, value = "select max(id) from project")
	public int getLastId();

	@Query(nativeQuery = true, value = "select * from project where id = (select max(id) from project)")
	public Project getLastProject();
	
	
	
	
	
	
	
}
