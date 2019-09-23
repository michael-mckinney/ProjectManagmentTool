package io.mrmmckinney.ppmtool.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mrmmckinney.ppmtool.services.ProjectService;
import io.mrmmckinney.ppmtool.domain.Project;

//this is our API

@RestController
@RequestMapping("/api/project")
public class ProjectController {
  
  //inject project service onto controller
  @Autowired
  private ProjectService projectService;
  
  //route to actually create a project
  @PostMapping("")
  // use responseEntity to have more control over JSON responses 
  // for React front end
  public ResponseEntity<Project> createNewProject(@RequestBody Project project) {
    //call project service to persist entered project
    projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    
  }
  
  
}
