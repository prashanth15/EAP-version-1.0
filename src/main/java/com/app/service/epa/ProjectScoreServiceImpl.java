package com.app.service.epa;

import com.app.dao.epa.ProjectScoresDAOImpl;
import com.app.model.epa.ProjectScores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectScoreServiceImpl {
    ProjectScoresDAOImpl projectScoresDAO;

    @Autowired
    public void setProjectScoresDAO(ProjectScoresDAOImpl projectScoresDAO) {
        this.projectScoresDAO = projectScoresDAO;
    }

    public void updateProjectScore(ProjectScores projectScores){
        projectScoresDAO.insertGroupScores(projectScores);
    }


    public Double getLatestProjectScore(int projectId){
        return projectScoresDAO.getLatestProjectScore(projectId);
    }

    public List getLatestTopScores(int numOfProjects, int numberOfCounts){
        return projectScoresDAO.getLatestTopScores(numOfProjects, numberOfCounts);
    }

    public List getLatestProjectScoreDetails(int projectId){
        return projectScoresDAO.getLatestProjectScoreDetails(projectId);
    }

    public List getHistoryOfProjectScores(int projectId){
        return projectScoresDAO.getHistoryOfProjectScores(projectId);
    }
}
