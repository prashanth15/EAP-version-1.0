package com.app.dao.openProject;


import com.app.dao.interfaces.openProject.ProjectsDAO;
import com.app.model.openProject.Projects;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_openproject")
public class ProjectsDAOImpl implements ProjectsDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_openproject") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Projects> getProjects(){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "FROM com.app.model.openProject.Projects";
        List<Projects> projects = session.createQuery(query).list();

        return projects;
    }

    @Override
    public String getProjectNameById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT project.name FROM com.app.model.openProject.Projects as project WHERE project.id = " + id;
        String projects = (String) session.createQuery(query).uniqueResult();

        return projects;
    }
}
