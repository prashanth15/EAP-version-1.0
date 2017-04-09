package com.app.service.interfaces.openProject;


import com.app.model.openProject.Projects;

import java.util.List;

public interface ProjectsService {

    public List<Projects> getProjects();
    public String getProjectNameById(int id);
}
