package com.app.Criteria.individual;

import com.app.model.openProject.PlanningDataModel;
import com.app.service.epa.SprintServiceImpl;
import com.app.service.openProject.WorkPackagesServiceImpl;
import com.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class WorkEfficiency {
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

    public double getCriteriaScore(int employeeId) {

        int sprintDuration = sprintService.getSprintDuration();
        double score = DEFAULT_SCORE;

        if (sprintDuration != ZERO) {

            int actualSprintDays = utils.springDurationCheck(sprintDuration);
            Date startDateInMillis = new Date(System.currentTimeMillis() - (actualSprintDays * DAY_IN_MS));
            Date endDateInMillis = new Date(System.currentTimeMillis());

            SimpleDateFormat dateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT);

            String startDate = dateFormat.format(startDateInMillis);
            String endDate = dateFormat.format(endDateInMillis);

            PlanningDataModel data = workPackagesService.getPlaningTheProject(employeeId, startDate, endDate);

            if (data.getEstimated_hours() != 0.0f && data.getHours() != 0.0f) {
                score = calculateScore(data);
            }
        }
        return score;
    }

    private double calculateScore(PlanningDataModel data) {
        double score = DEFAULT_SCORE;

        double scoreRange =  data.getEstimated_hours() - data.getHours();

        if (scoreRange >= 0) {
            score = 100;
        } else if (scoreRange >= -10 && scoreRange < 0) {
            score = 95;
        } else if (scoreRange >= -20 && scoreRange < -10) {
            score = 80;
        } else if (scoreRange >= -30 && scoreRange < -20) {
            score = 70;
        } else if (scoreRange < -30) {
            score = 50;
        }

        return score;
    }

}
