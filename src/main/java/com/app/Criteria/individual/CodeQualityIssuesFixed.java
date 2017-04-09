package com.app.Criteria.individual;

import com.app.service.epa.SprintServiceImpl;
import com.app.service.openProject.UserOpenProjectServiceImpl;
import com.app.service.sonar.IssuesServiceImpl;
import com.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CodeQualityIssuesFixed {
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
    @Qualifier(value = "sprintServiceImpl")
    SprintServiceImpl sprintService;

    @Autowired
    Utils utils;

    public double getCriteriaScore(int employeeId) {

        int sprintDuration = sprintService.getSprintDuration();
        double score = DEFAULT_SCORE;
        List results = null;

        if (sprintDuration != ZERO) {

            int actualSprintDays = utils.springDurationCheck(sprintDuration);
            Date startDateInMillis = new Date(System.currentTimeMillis() - (actualSprintDays * DAY_IN_MS));
            Date endDateInMillis = new Date(System.currentTimeMillis());

            String startDate = Long.toString(startDateInMillis.getTime());
            String endDate = Long.toString(endDateInMillis.getTime());

            String userLogin = userOpenProjectService.getLoginByUserId(employeeId);

            if (userLogin != null) {
                results = issuesService.getSeverityFixedCount(userLogin, startDate, endDate);
            }

            if (results != null && !results.isEmpty()) {
                score = calculateScore(results);
            }
        }
        return score;
    }

    private double calculateScore(List<Object []> results) {

        int info = 0;
        int minor = 0;
        int major = 0;
        int critical= 0;
        int blocker = 0;

        for(Object [] obj : results){

            String type = obj[0].toString();

            switch (type){
                case INFO :
                    long infoCount = (Long) obj[1];

                    if(infoCount > 0 && infoCount <= 10){
                        info = 3;
                    } else if(infoCount > 10 && infoCount <= 20){
                        info = 5;
                    } else if(infoCount > 20 && infoCount <= 30){
                        info = 8;
                    } else if(infoCount > 30){
                        info = 10;
                    }
                    break;

                case MINOR :
                    long minorCount = (Long) obj[1];

                    if(minorCount > 0 && minorCount <= 10){
                        minor = 3;
                    } else if(minorCount > 10 && minorCount <= 20){
                        minor = 5;
                    } else if(minorCount > 20 && minorCount <= 30){
                        minor = 8;
                    } else if(minorCount > 30){
                        minor = 10;
                    }
                    break;

                case MAYOR :
                    long majorCount = (Long) obj[1];

                    if(majorCount > 0 && majorCount <= 10){
                        major = 8;
                    } else if(majorCount > 10 && majorCount <= 20){
                        major = 10;
                    } else if(majorCount > 20 && majorCount <= 30){
                        major = 15;
                    } else if(majorCount > 30){
                        major = 20;
                    }
                    break;

                case CRITICAL :
                    long criticalCount = (Long) obj[1];

                    if(criticalCount > 0 && criticalCount <= 10){
                        critical = 10;
                    } else if(criticalCount > 10 && criticalCount <= 20){
                        critical = 15;
                    } else if(criticalCount > 20 && criticalCount <= 30){
                        critical = 20;
                    } else if(criticalCount > 30){
                        critical = 30;
                    }
                    break;

                case BLOCKER :
                    long blockerCount = (Long) obj[1];

                    if(blockerCount > 0 && blockerCount <= 10){
                        blocker = 15;
                    } else if(blockerCount > 10 && blockerCount <= 20){
                        blocker = 20;
                    } else if(blockerCount > 20 && blockerCount <= 30){
                        blocker = 25;
                    } else if(blockerCount > 30){
                        blocker = 30;
                    }
                    break;
            }
        }

        double totalScore = info + major + minor + critical + blocker;

        return totalScore;
    }
}
