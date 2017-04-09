package com.app.dao.epa;

import com.app.model.epa.PersonalTraits;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_epa")
public class PersonalTraitsDAOImpl {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_epa")SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int getPersonalTraits(int id){
        int scoreD = 0;
        Session session =this.sessionFactory.getCurrentSession();
        String query = "SELECT pt.score FROM com.app.model.epa.PersonalTraits as pt WHERE pt.emp_id = " + id;
        Integer score = (Integer) session.createQuery(query).uniqueResult();
        if(score != null){
            scoreD = score;
        }
        return scoreD;
    }

    public void setPersonalTraitsScore(PersonalTraits personalTraits){
        Session session =this.sessionFactory.getCurrentSession();
        session.update(personalTraits);
    }
}
