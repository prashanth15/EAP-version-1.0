package com.app.SchedularUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskScheduler {
//    private static final long DAY_IN_MS = 1000 * 60 * 60 * 24;
    private static final long DAY_IN_MS = 1000;

    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private boolean isInitialized = false;
    private TaskExecutor task;

    public void scheduleTask(Date startTime, int days){
        task = new TaskExecutor();

        long period = DAY_IN_MS * days;

        if(isInitialized){
            threadPoolTaskScheduler.destroy();
        }
        threadPoolTaskScheduler.initialize();
        threadPoolTaskScheduler.scheduleAtFixedRate(task, startTime, period);
        isInitialized = true;
    }
}
