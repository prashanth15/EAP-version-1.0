package com.app.dao.sonar;

import com.app.dao.interfaces.sonar.ProjectDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_sonar")
public class ProjectDAOImpl implements ProjectDAO{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_sonar") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public String getProjectUuid(String name){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT pro.project_uuid FROM com.app.model.sonar.Project as pro WHERE pro.name = '" + name + "'";

        String uuid =(String) session.createQuery(query).uniqueResult();

        return uuid;
    }
}
