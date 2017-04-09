package com.app.Criteria.projects;

import com.app.model.openProject.WorkPackages;
import com.app.service.epa.SprintServiceImpl;
import com.app.service.openProject.WorkPackagesServiceImpl;
import com.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProjectTeamWorkCompletion {
    private static final int NEW = 1;
    private static final int IN_PROGRESS = 7;
    private static final int CLOSED = 13;
    private static final int ON_HOLD = 14;
    private static final double DEFAULT_SCORE = 0.0;
    private static final int ZERO = 0;
    private static final long DAY_IN_MS = 1000 * 60 * 60 * 24;
    private static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    @Autowired
    WorkPackagesServiceImpl workPackagesService;

    @Autowired
    @Qualifier(value = "sprintServiceImpl")
    SprintServiceImpl sprintService;

    @Autowired
    Utils utils;
    /**
     * method to get criteria score
     * @param projectId
     * @return score
     */
    public double getCriteriaScore(int projectId) {

        int sprintDuration = sprintService.getSprintDuration();
        double score = DEFAULT_SCORE;

        if (sprintDuration != ZERO) {

            int actualSprintDays = utils.springDurationCheck(sprintDuration);
            Date startDateInMillis = new Date(System.currentTimeMillis() - (actualSprintDays * DAY_IN_MS));
            Date endDateInMillis = new Date(System.currentTimeMillis());

            SimpleDateFormat dateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT);

            String startDate = dateFormat.format(startDateInMillis);
            String endDate = dateFormat.format(endDateInMillis);

            List<WorkPackages> data = workPackagesService.getStatusOfTasksProjects(projectId, startDate, endDate);

            if(!data.isEmpty()){
                score = calculateScore(data);
            }
        }
        return score;
    }


    /**
     * Method for calculate score
     * @param workPackages
     * @return criteria score
     */
    private double calculateScore(List<WorkPackages> workPackages){
        double score = DEFAULT_SCORE;

        int newCount = ZERO;
        int inProgressCount = ZERO;
        int closedCount = ZERO;
        int onHoldCount = ZERO;

        for(WorkPackages workPackage : workPackages){
            int statusId = workPackage.getStatus_id();

            switch (statusId) {
                case NEW:
                    newCount++;
                    break;
                case IN_PROGRESS:
                    inProgressCount++;
                    break;
                case CLOSED:
                    closedCount++;
                    break;
                case ON_HOLD:
                    onHoldCount++;
                    break;
                default:
            }
        }

        int totalTask = newCount + inProgressCount + closedCount + onHoldCount;

        if(totalTask != ZERO){
            double completedScore = (closedCount*100.0f)/totalTask;
            double inProgressScore = ((inProgressCount*100.0f)/totalTask)*0.5;
            score = round(completedScore + inProgressScore , 2);
        }

        return score;
    }

    /**
     * round a double value
     * @param value
     * @param places
     * @return
     */
    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
