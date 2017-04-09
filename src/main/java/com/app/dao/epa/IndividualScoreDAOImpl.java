package com.app.dao.epa;

import com.app.model.epa.IndividualScores;
import com.app.requestBody.Individual;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

import java.sql.Timestamp;
import java.util.List;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_epa")
public class IndividualScoreDAOImpl {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_epa")SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertIndividualScore(IndividualScores individualScores){
        Session session = this.sessionFactory.getCurrentSession();
        session.save(individualScores);
        session.flush();
    }

    public List getLatestTopScores(int numOfUsers, int numberOfCounts){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT indi.emp_id, indi.total_score FROM (SELECT * FROM individualscores as ins ORDER BY ins.updated_at DESC LIMIT " + numOfUsers + ") AS indi ORDER BY indi.total_score DESC LIMIT " + numberOfCounts;
        List scores = session.createSQLQuery(query).list();
        return  scores;
    }

    public IndividualScores getLatestIndividualScore(int empId){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT * FROM individualscores as indi WHERE indi.emp_id = " + empId + " ORDER BY indi.updated_at DESC LIMIT 1";
        List<Object []> obj = session.createSQLQuery(query).list();
        IndividualScores individualScores = null;
        if(obj != null && !obj.isEmpty()){
            individualScores = new IndividualScores();
            Object [] objArray = obj.get(0);
            individualScores.setEmp_id((int)objArray[1]);
            individualScores.setWork_completion((double) objArray[2]);
            individualScores.setPlanning_the_project((double) objArray[3]);
            individualScores.setWork_efficiency((double) objArray[4]);
            individualScores.setDefects_count((double) objArray[5]);
            individualScores.setFixed_defects_count((double) objArray[6]);
            individualScores.setCode_quality_issue((double) objArray[7]);
            individualScores.setCqi_fixed((double) objArray[8]);
            individualScores.setProject_team_contribution((double) objArray[9]);
            individualScores.setPersonal_traits((double) objArray[10]);
            individualScores.setUpdated_at(objArray[11].toString());
            individualScores.setTotal_score((double)objArray[12]);
        }
        return individualScores;
    }

    public List getIndividualScores(int empId){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT indi.total_score FROM individualscores AS indi WHERE indi.emp_id = " + empId + " ORDER BY indi.updated_at ASC";
        return session.createSQLQuery(query).list();
    }

}
