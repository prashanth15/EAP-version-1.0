package com.app.service.sonar;

import com.app.dao.interfaces.sonar.IssuesDAO;
import com.app.service.interfaces.sonar.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesServiceImpl implements IssuesService {

    IssuesDAO issuesDAO;

    @Autowired
    public void setIssuesDAO(@Qualifier("IssuesDAO") IssuesDAO issuesDAO) {
        this.issuesDAO = issuesDAO;
    }

    public List getSeverityCount(String authorLogin, String startDate, String endDate){
        return issuesDAO.getSeverityCount(authorLogin, startDate, endDate);
    }

    public List getSeverityFixedCount(String assignee, String startDate, String endDate){
        return issuesDAO.getSeverityFixedCount(assignee, startDate, endDate);
    }

    public List getProjectSeverityCount(String projectUuid, String startDate, String endDate){
        return issuesDAO.getProjectSeverityCount(projectUuid, startDate, endDate);
    }
}
