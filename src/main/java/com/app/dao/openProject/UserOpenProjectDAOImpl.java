package com.app.dao.openProject;

import com.app.dao.interfaces.openProject.UsersOpenProjectDAO;
import com.app.model.openProject.UsersOpenProject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_openproject")
public class UserOpenProjectDAOImpl implements UsersOpenProjectDAO {

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_openproject")SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "select u.id, u.firstname from com.app.model.openProject.UsersOpenProject as u WHERE u.status = 1 AND u.login != 'admin'";
        List<Object[]> users = session.createQuery(query).list();
        return users;
    }

    @SuppressWarnings("unchecked")
    public String getUserLoginById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT user.login FROM com.app.model.openProject.UsersOpenProject as user WHERE user.id = " + id;
        String login = (String) session.createQuery(query).uniqueResult();
        return login;
    }

    @SuppressWarnings("unchecked")
    public String getFirstName(int id){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT user.firstname FROM com.app.model.openProject.UsersOpenProject as user WHERE user.id = " + id;
        String name = (String) session.createQuery(query).uniqueResult();
        return name;
    }
}
