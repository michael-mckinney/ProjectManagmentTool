package io.mrmmckinney.ppmtool.web;




import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mrmmckinney.ppmtool.services.MapValidationErrorService;
import io.mrmmckinney.ppmtool.services.ProjectService;
import io.mrmmckinney.ppmtool.domain.Project;

//this is our API

@RestController
@RequestMapping("/api/project")
public class ProjectController {
  
  //inject project service onto controller
  @Autowired
  private ProjectService projectService;
  
  @Autowired
  private MapValidationErrorService mapValidationErrorService;
  
  //route to actually create a project
  @PostMapping("")
  // use responseEntity to have more control over JSON responses 
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
    
    ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
    if (errorMap!= null) {
      return errorMap;
    }
    
    //call project service to persist entered project
    Project project1 = projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    
  }
  
  @GetMapping("/{projectId}")
  public ResponseEntity<?> getProjectbyID(@PathVariable String projectId) {
    
    Project project = projectService.findByIdentifier(projectId);
    
    return new ResponseEntity<Project>(project, HttpStatus.OK);
  }
  
  
}
