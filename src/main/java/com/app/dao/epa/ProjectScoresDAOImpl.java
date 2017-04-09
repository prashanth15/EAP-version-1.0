package com.app.dao.epa;

import com.app.model.epa.ProjectScores;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_epa")
public class ProjectScoresDAOImpl {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_epa")SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertGroupScores(ProjectScores projectScores){
        Session session = this.sessionFactory.getCurrentSession();
        session.save(projectScores);
        session.flush();
    }

    public Double getLatestProjectScore(int projectId){
        Double projectScore = 0.0D;
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT ps.total_score FROM com.app.model.epa.ProjectScores as ps WHERE ps.project_id = " + projectId
                + " ORDER BY ps.updated_at DESC";
        List score = session.createQuery(query).list();

        if(score != null){
            projectScore = (Double)score.get(0);
        }

        return projectScore;
    }

    public List getLatestProjectScoreDetails(int projectId){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT * FROM project_scores as ps WHERE ps.project_id = " + projectId
                + " ORDER BY ps.updated_at DESC LIMIT 1";
        return session.createSQLQuery(query).list();
    }

    public List getLatestTopScores(int numOfProjects, int numberOfCounts){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT ps.project_id, ps.total_score FROM (SELECT * FROM project_scores as proj ORDER BY proj.updated_at DESC LIMIT " + numOfProjects + ") AS ps ORDER BY ps.total_score DESC LIMIT " + numberOfCounts;
        List scores = session.createSQLQuery(query).list();
        return  scores;
    }

    public List getHistoryOfProjectScores(int projectId){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT p.total_score FROM project_scores as p WHERE p.project_id = " + projectId + " ORDER BY p.updated_at ASC";
        return session.createSQLQuery(query).list();
    }
}
