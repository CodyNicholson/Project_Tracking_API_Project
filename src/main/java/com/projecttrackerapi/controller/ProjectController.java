package com.projecttrackerapi.controller;

import com.projecttrackerapi.dtos.GenericResponseModel;
import com.projecttrackerapi.dtos.ProjectDto;
import com.projecttrackerapi.service.project.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GenericResponseModel> addProjectToBoard(@RequestBody ProjectDto project) {
        ProjectDto newProject = projectService.saveOrUpdateProject(project);
        GenericResponseModel responseModel = new GenericResponseModel(201, newProject);
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponseModel> getAllPTs(){
        Iterable<ProjectDto> projects = projectService.getAllProjects();
        GenericResponseModel responseModel = new GenericResponseModel(200, projects);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponseModel> getProjectById(@PathVariable("projectId") String projectId){
        ProjectDto projectDto = projectService.getProjectById(UUID.fromString(projectId));
        GenericResponseModel responseModel = new GenericResponseModel(200, projectDto);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponseModel> deleteProject(@PathVariable("projectId") String projectId){
        ProjectDto deleteProjectResponseModel = projectService.deleteProjectById(UUID.fromString(projectId));
        GenericResponseModel responseModel = new GenericResponseModel(200, deleteProjectResponseModel);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
