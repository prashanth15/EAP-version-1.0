package com.app.dao.sonar;

import com.app.dao.interfaces.sonar.IssuesDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_sonar")
public class IssuesDAOImpl implements IssuesDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_sonar") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List getSeverityCount(String authorLogin, String startDate, String endDate){
        Session session = this.sessionFactory.getCurrentSession();

        String query = "SELECT iss.severity, COUNT(*) FROM com.app.model.sonar.Issues as iss WHERE iss.author_login = '" +
                authorLogin + "' AND iss.created_at BETWEEN " + startDate + " AND " + endDate + " AND status = 'OPEN' GROUP BY iss.severity";
        List result = session.createQuery(query).list();
        return result;
    }

    @SuppressWarnings("unchecked")
    public List getProjectSeverityCount(String projectUuid, String startDate, String endDate){
        Session session = this.sessionFactory.getCurrentSession();

        String query = "SELECT iss.severity, COUNT(*) FROM com.app.model.sonar.Issues as iss WHERE iss.project_uuid = '" +
                projectUuid + "' AND iss.created_at BETWEEN " + startDate + " AND " + endDate + " AND status = 'OPEN' GROUP BY iss.severity";
        List result = session.createQuery(query).list();
        return result;
    }

    @SuppressWarnings("unchecked")
    public List getSeverityFixedCount(String assignee, String startDate, String endDate){
        Session session = this.sessionFactory.getCurrentSession();

        String query = "SELECT iss.severity, COUNT(*) FROM com.app.model.sonar.Issues as iss WHERE iss.assignee = '" +
                assignee + "' AND iss.updated_at BETWEEN " + startDate + " AND " + endDate + " AND status = 'RESOLVED' GROUP BY iss.severity";
        List result = session.createQuery(query).list();
        return result;
    }


}
