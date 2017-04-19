package com.app.SchedularUtils;

import com.app.Criteria.handler.ScoreHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskExecutor implements Runnable {

    @Autowired
    ScoreHandler scoreHandler;

    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("My job runs at : " + simpleDateFormat.format(date));
//        scoreHandler.scheduleScoreHandlers();
    }

}