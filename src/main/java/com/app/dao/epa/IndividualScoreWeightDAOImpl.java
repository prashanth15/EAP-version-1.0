package com.app.dao.epa;

import com.app.model.epa.IndividualScoreWeight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_epa")
public class IndividualScoreWeightDAOImpl {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_epa")SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public IndividualScoreWeight getIndividualScore(int empId){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "FROM com.app.model.epa.IndividualScoreWeight as isw where isw.emp_id = " + empId;
        IndividualScoreWeight individualScoreWeight = (IndividualScoreWeight) session.createQuery(query).uniqueResult();
        return individualScoreWeight;
    }

    public void updateScore(IndividualScoreWeight individualScoreWeight){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(individualScoreWeight);
    }
}
