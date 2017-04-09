package com.app.SchedularUtils;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

//@Component
public class QueryScheduler extends QuartzJobBean {

    private QueryProcessor queryProcessor;

    @Autowired
    public void setQueryProcessor(QueryProcessor queryProcessor) {
        this.queryProcessor = queryProcessor;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobContext)  throws JobExecutionException {
        queryProcessor.printX();
//        queryProcessor.scheduleScoreUpdater();
    }
}
