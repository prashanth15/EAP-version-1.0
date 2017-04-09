package com.app.utils;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class Utils {
    private static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    private static final long DAY_IN_MS = 1000 * 60 * 60 * 24;

    public int springDurationCheck(int sprintDuration){
        Date currentMilli = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        String todayDate = dateFormat.format(currentMilli);
        Calendar c = Calendar.getInstance();

        int count = 0;
        int i = sprintDuration;
        int dayIncreases = 0;
        while(i > 0){
            c.setTime(currentMilli);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek == 1 || dayOfWeek == 7){
                dayIncreases++;
            } else {
                i--;
            }
            count++;
            currentMilli = new Date(System.currentTimeMillis() - (count * DAY_IN_MS));
        }

        return sprintDuration + dayIncreases;
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
