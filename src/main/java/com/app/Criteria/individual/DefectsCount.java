package com.app.Criteria.individual;

import com.app.service.epa.SprintServiceImpl;
import com.app.service.openProject.WorkPackagesServiceImpl;
import com.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DefectsCount {
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

            Long defects = workPackagesService.getDefectCount(employeeId, startDate, endDate);

            if(defects >= 0) {
                score = calculateScore(defects);
            }
        }
        return score;
    }

    private double calculateScore(long defects) {
        double score = 0;

        if(defects == 0L){
            score = 100;
        } else if(defects > 0 && defects <= 10){
            score = 90;
        } else if (defects > 10 && defects <= 20){
            score = 80;
        } else if(defects > 20 && defects <= 30){
            score = 70;
        } else if(defects > 30){
            score = 50;
        }

        return score;
    }
}
