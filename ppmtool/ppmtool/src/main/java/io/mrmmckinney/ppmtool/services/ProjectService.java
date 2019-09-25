package io.mrmmckinney.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.mrmmckinney.ppmtool.domain.Project;
import io.mrmmckinney.ppmtool.exceptions.ProjectIdException;
import io.mrmmckinney.ppmtool.repositories.ProjectRepository;

//service to connect to the CRUD repository interface
@Service
public class ProjectService {
  
  @Autowired
  private ProjectRepository projectRepository;
  
  //enable saving of project
  public Project saveOrUpdateProject(Project project) {
    
    //handle custom exception
    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
      return projectRepository.save(project);
    }
    catch (Exception e) {
      throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase() + " already exists");
    }
    
  }
  
  public Project findByIdentifier(String projectId) {
    
    Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    
    if (project == null) {
      throw new ProjectIdException("Project ID does not exist");
    }
    
    return project;
  }
  
  public Iterable<Project> findAllProjects() {
    return projectRepository.findAll();
  }
  
  public void deleteProjectByIdentifier (String projectId) {
    Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    
    if (project == null) {
      throw new ProjectIdException("Cannot delete. Project ID does not exist");
    }
    
  }
  
  //more logic when I add users

}
