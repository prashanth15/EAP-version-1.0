package com.app.Criteria.projects;

import com.app.service.epa.SprintServiceImpl;
import com.app.service.interfaces.openProject.ProjectsService;
import com.app.service.interfaces.sonar.ProjectService;
import com.app.service.openProject.UserOpenProjectServiceImpl;
import com.app.service.sonar.IssuesServiceImpl;
import com.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectTeamCodeQuality {
    private static final double DEFAULT_SCORE = 0.0;
    private static final int ZERO = 0;
    private static final long DAY_IN_MS = 1000 * 60 * 60 * 24;

    private static final String INFO = "INFO";
    private static final String MINOR = "MINOR";
    private static final String MAYOR = "MAJOR";
    private static final String CRITICAL = "CRITICAL";
    private static final String BLOCKER = "BLOCKER";

    @Autowired
    UserOpenProjectServiceImpl userOpenProjectService;

    @Autowired
    IssuesServiceImpl issuesService;

    @Autowired
    ProjectsService projectsService;

    @Autowired
    ProjectService projectService;

    @Autowired
    @Qualifier(value = "sprintServiceImpl")
    SprintServiceImpl sprintService;

    @Autowired
    Utils utils;

    public double getCriteriaScore(int projectId) {

        int sprintDuration = sprintService.getSprintDuration();
        double score = DEFAULT_SCORE;
        List results = null;

        if (sprintDuration != ZERO) {

            int actualSprintDays = utils.springDurationCheck(sprintDuration);
            Date startDateInMillis = new Date(System.currentTimeMillis() - (actualSprintDays * DAY_IN_MS));
            Date endDateInMillis = new Date(System.currentTimeMillis());

            String startDate = Long.toString(startDateInMillis.getTime());
            String endDate = Long.toString(endDateInMillis.getTime());

            String projectName = projectsService.getProjectNameById(projectId);

            if(projectName != null) {
                String projectUuid = projectService.getProjectUuid(projectName);
                if (projectUuid != null) {
                    results = issuesService.getProjectSeverityCount(projectUuid, startDate, endDate);
                }
            }

            if (results != null && !results.isEmpty()) {
                score = calculateScore(results);
            }
        }
        return score;
    }

    private double calculateScore(List<Object []> results) {

        int infoReduction = 0;
        int minorReduction = 0;
        int majorReduction = 0;
        int criticalReduction = 0;
        int blockerReduction = 0;

        for(Object [] obj : results){

            String type = obj[0].toString();

            switch (type){
                case INFO :
                    long infoCount = (Long) obj[1];

                    if(infoCount > 0 && infoCount <= 10){
                        infoReduction = 3;
                    } else if(infoCount > 10 && infoCount <= 20){
                        infoReduction = 5;
                    } else if(infoCount > 20 && infoCount <= 30){
                        infoReduction = 8;
                    } else if(infoCount > 30){
                        infoReduction = 10;
                    }
                    break;

                case MINOR :
                    long minorCount = (Long) obj[1];

                    if(minorCount > 0 && minorCount <= 10){
                        minorReduction = 3;
                    } else if(minorCount > 10 && minorCount <= 20){
                        minorReduction = 5;
                    } else if(minorCount > 20 && minorCount <= 30){
                        minorReduction = 8;
                    } else if(minorCount > 30){
                        minorReduction = 10;
                    }
                    break;

                case MAYOR :
                    long majorCount = (Long) obj[1];

                    if(majorCount > 0 && majorCount <= 10){
                        majorReduction = 8;
                    } else if(majorCount > 10 && majorCount <= 20){
                        majorReduction = 10;
                    } else if(majorCount > 20 && majorCount <= 30){
                        majorReduction = 15;
                    } else if(majorCount > 30){
                        majorReduction = 20;
                    }
                    break;

                case CRITICAL :
                    long criticalCount = (Long) obj[1];

                    if(criticalCount > 0 && criticalCount <= 10){
                        criticalReduction = 10;
                    } else if(criticalCount > 10 && criticalCount <= 20){
                        criticalReduction = 15;
                    } else if(criticalCount > 20 && criticalCount <= 30){
                        criticalReduction = 20;
                    } else if(criticalCount > 30){
                        criticalReduction = 30;
                    }
                    break;

                case BLOCKER :
                    long blockerCount = (Long) obj[1];

                    if(blockerCount > 0 && blockerCount <= 10){
                        blockerReduction = 15;
                    } else if(blockerCount > 10 && blockerCount <= 20){
                        blockerReduction = 20;
                    } else if(blockerCount > 20 && blockerCount <= 30){
                        blockerReduction = 25;
                    } else if(blockerCount > 30){
                        blockerReduction = 30;
                    }
                    break;
            }
        }

        int totalReduction = infoReduction + majorReduction + minorReduction + criticalReduction + blockerReduction;

        double score = 100 - totalReduction;

        return score;
    }
}
