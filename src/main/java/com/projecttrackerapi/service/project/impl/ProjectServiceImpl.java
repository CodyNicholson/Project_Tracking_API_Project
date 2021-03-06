package com.projecttrackerapi.service.project.impl;

import com.projecttrackerapi.constants.Constants;
import com.projecttrackerapi.service.dao.impl.ProjectDaoImpl;
import com.projecttrackerapi.error.restCustomExceptions.BadRequestException;
import com.projecttrackerapi.error.restCustomExceptions.NotFoundException;
import com.projecttrackerapi.dtos.ProjectDto;
import com.projecttrackerapi.service.project.ProjectService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectDaoImpl projectDao;

    public ProjectServiceImpl(ProjectDaoImpl projectDao) {
        this.projectDao = projectDao;
    }

    public ProjectDto saveOrUpdateProject(ProjectDto project) {
        String badRequestMessage = "";
        if (project.getName() == null || project.getName().isEmpty()) {
            badRequestMessage += Constants.PROJECT_MUST_HAVE_NAME;
        }
        if (project.getDescription() == null || project.getDescription().isEmpty()) {
            badRequestMessage += Constants.PROJECT_MUST_HAVE_DESCRIPTION;
        }
        if (!badRequestMessage.isEmpty()) {
            throw new BadRequestException(badRequestMessage.trim(), null);
        }

        if (project.getStart_date() == null) {
            project.setStart_date(new Date());
        }

        return projectDao.saveOrUpdateProject(project);
    }

    public List<ProjectDto> getAllProjects() {
        return projectDao.findAllProjects();
    }

    public ProjectDto getProjectById(UUID projectId) {
        return projectDao.findProjectById(projectId);
    }

    public ProjectDto deleteProjectById(UUID projectId) {
        return projectDao.deleteProject(projectId);
    }
}
