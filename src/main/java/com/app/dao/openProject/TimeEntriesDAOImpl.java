package com.app.dao.openProject;

import com.app.model.openProject.WorkPackages;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_openproject")
public class TimeEntriesDAOImpl {
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_openproject")SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
