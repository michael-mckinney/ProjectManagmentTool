package io.mrmmckinney.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.mrmmckinney.ppmtool.domain.Project;
import io.mrmmckinney.ppmtool.repositories.ProjectRepository;

//service to connect to the CRUD repository interface
@Service
public class ProjectService {
  
  @Autowired
  private ProjectRepository projectRepository;
  
  //enable saving of project
  public Project saveOrUpdateProject(Project project) {
    return projectRepository.save(project);
  }
  
  //more logic when I add users

}
