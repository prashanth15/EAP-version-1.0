package com.app.Criteria.handler;

import com.app.Criteria.projects.ProjectTeamCodeQuality;
import com.app.Criteria.projects.ProjectTeamDefectCount;
import com.app.Criteria.projects.ProjectTeamEfficiency;
import com.app.Criteria.projects.ProjectTeamWorkCompletion;
import com.app.model.epa.ProjectScores;
import com.app.service.epa.ProjectScoreServiceImpl;
import com.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GroupScoreHandler {

    @Autowired
    ProjectTeamWorkCompletion projectTeamWorkCompletion;

    @Autowired
    ProjectTeamEfficiency projectTeamEfficiency;

    @Autowired
    ProjectTeamCodeQuality projectTeamCodeQuality;

    @Autowired
    ProjectTeamDefectCount projectTeamDefectCount;

    @Autowired
    @Qualifier("proScore")
    ProjectScoreServiceImpl projectScoreService;

    @Autowired
    Utils utils;

    public void updateProjectScore(int projectId) {

        double projectCompletion = projectTeamWorkCompletion.getCriteriaScore(projectId);
        double projectEfficiency = projectTeamEfficiency.getCriteriaScore(projectId);
        double projectCodeQuality = projectTeamCodeQuality.getCriteriaScore(projectId);
        double projectDefectCount = projectTeamDefectCount.getCriteriaScore(projectId);

        //get current time
        Date currentTime = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updatedAt = dateFormat.format(currentTime);

        double totalScore = projectCompletion * 0.25 +
                projectEfficiency * 0.25 +
                projectCodeQuality * 0.25 +
                projectDefectCount * 0.25;

        ProjectScores projectScores = new ProjectScores();

        projectScores.setProject_id(projectId);
        projectScores.setTeam_work_completion(utils.round(projectCompletion, 2));
        projectScores.setTeam_efficiency(utils.round(projectEfficiency, 2));
        projectScores.setTeam_code_quality(utils.round(projectCodeQuality, 2));
        projectScores.setTeam_defects_count(utils.round(projectDefectCount, 2));
        projectScores.setUpdated_at(updatedAt);
        projectScores.setTotal_score(utils.round(totalScore, 2));

        projectScoreService.updateProjectScore(projectScores);
    }
}
