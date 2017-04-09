package com.app.dao.interfaces.openProject;

import com.app.model.openProject.Projects;
import java.util.List;

public interface ProjectsDAO {
    public List<Projects> getProjects();
    public String getProjectNameById(int id);
}
