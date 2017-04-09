package com.app.Criteria.individual;

import com.app.service.epa.SprintServiceImpl;
import com.app.service.interfaces.time_clock.InfoService;
import com.app.service.openProject.UserOpenProjectServiceImpl;
import com.app.service.openProject.WorkPackagesServiceImpl;
import com.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Attendance {
    private static final int DEFAULT = 0;
    private static final int ZERO = 0;
    private static final long DAY_IN_MS = 1000 * 60 * 60 * 24;
    private static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    @Qualifier(value = "sprintServiceImpl")
    SprintServiceImpl sprintService;

    @Autowired
    UserOpenProjectServiceImpl userOpenProjectService;

    @Autowired
    InfoService infoService;

    @Autowired
    Utils utils;

    public long getAttendanceDays(int employeeId) {

        int sprintDuration = sprintService.getSprintDuration();
        long days = DEFAULT;

        if (sprintDuration != ZERO) {

            int actualSprintDays = utils.springDurationCheck(sprintDuration);
            Date startDateInMillis = new Date(System.currentTimeMillis() - (actualSprintDays * DAY_IN_MS));            Date endDateInMillis = new Date(System.currentTimeMillis());

            SimpleDateFormat dateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT);

            String startDate = dateFormat.format(startDateInMillis);
            String endDate = dateFormat.format(endDateInMillis);

            String firstName = userOpenProjectService.getFirstName(employeeId);

            if(firstName != null){
                days = infoService.getAttendance(firstName, startDate, endDate);
            }
        }
        return days;
    }
}
