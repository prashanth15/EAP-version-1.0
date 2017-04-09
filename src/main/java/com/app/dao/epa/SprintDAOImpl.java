package com.app.dao.epa;

import com.app.dao.interfaces.epa.SprintDAO;
import com.app.model.epa.Sprint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;


@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_epa")
public class SprintDAOImpl implements SprintDAO {
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_epa")SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addSprint(Sprint sprint){
        Session session = sessionFactory.getCurrentSession();
        session.persist(sprint);
//        session.createQuery("insert into com.app.model.epa.Sprint " + sprint);
//        session.save(sprint);
//        session.getTransaction().commit();
    }

    public void updateSprint(Sprint sprint){
        Session session = sessionFactory.getCurrentSession();
        session.update(sprint);
    }

    public int getSprintDuration(){
        int days = 0;
        Session session = sessionFactory.getCurrentSession();
        List<Sprint> list = session.createQuery("from com.app.model.epa.Sprint as s where s.id = 1").list();

        if(!list.isEmpty()){
            days = list.get(0).getNo_of_days();
        }
        return days;
    }


}
