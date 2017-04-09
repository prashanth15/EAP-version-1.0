package com.app.SchedularUtils;

import com.app.Criteria.handler.ScoreHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;


public class QueryProcessor {

    @Autowired
    ScoreHandler scoreHandler;

    public void printX(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        System.out.println("my job : " + dateFormat.format(date));
    }

    public void scheduleScoreUpdater(){
        scoreHandler.scheduleScoreHandlers();
    }
}
