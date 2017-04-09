package com.app.service.openProject;

import com.app.dao.interfaces.openProject.ProjectsDAO;
import com.app.model.openProject.Projects;
import com.app.service.interfaces.openProject.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsServiceImpl implements ProjectsService {

    ProjectsDAO projectsDAO;

    @Autowired
    public void setProjectsDAO(@Qualifier("ProjectsDAO") ProjectsDAO projectsDAO) {
        this.projectsDAO = projectsDAO;
    }

    @Override
    public List<Projects> getProjects(){
        return projectsDAO.getProjects();
    }

    @Override
    public String getProjectNameById(int id){
        return projectsDAO.getProjectNameById(id);
    }
}
