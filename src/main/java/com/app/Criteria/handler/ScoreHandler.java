package com.app.Criteria.handler;

import com.app.model.openProject.Projects;
import com.app.model.openProject.UsersOpenProject;
import com.app.service.openProject.ProjectsServiceImpl;
import com.app.service.openProject.UserOpenProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreHandler {
    @Autowired
    IndividualScoreHandler individualScoreHandler;

    @Autowired
    GroupScoreHandler groupScoreHandler;

    @Autowired
    UserOpenProjectServiceImpl userOpenProjectService;

    @Autowired
    ProjectsServiceImpl projectsService;

    public void scheduleScoreHandlers(){
        projectScoreGenerator();
        individualScoreGenerator();
    }

    private void individualScoreGenerator(){
        List<Object[]> usersList = userOpenProjectService.listUsers();

        if(usersList != null && !usersList.isEmpty()){
            for(Object[] user : usersList){
                individualScoreHandler.updateIndividualScore((Integer)user[0]);
            }
        }
    }

    private void projectScoreGenerator(){
        List<Projects> projectsList = projectsService.getProjects();

        if(projectsList != null && !projectsList.isEmpty()){
            for(Projects project : projectsList){
                groupScoreHandler.updateProjectScore(project.getId());
            }
        }
    }
}
