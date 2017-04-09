package com.app.service.interfaces.sonar;

import java.util.List;

public interface IssuesService {
    public List getSeverityCount(String authorLogin, String startDate, String endDate);
    public List getSeverityFixedCount(String assignee, String startDate, String endDate);
    public List getProjectSeverityCount(String projectUuid, String startDate, String endDate);
}
