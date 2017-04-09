package com.app.service.sonar;

import com.app.dao.interfaces.sonar.ProjectDAO;
import com.app.service.interfaces.sonar.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    ProjectDAO projectsDAO;

    @Autowired
    public void setProjectsDAO(@Qualifier("ProjectDAO") ProjectDAO projectsDAO) {
        this.projectsDAO = projectsDAO;
    }

    @Override
    public String getProjectUuid(String name){
        return projectsDAO.getProjectUuid(name);
    }
}
