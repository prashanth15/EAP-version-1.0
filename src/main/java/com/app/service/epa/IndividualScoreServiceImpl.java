package com.app.service.epa;

import com.app.dao.epa.IndividualScoreDAOImpl;
import com.app.model.epa.IndividualScores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualScoreServiceImpl {
    IndividualScoreDAOImpl individualScoreDAO;

    @Autowired
    public void setIndividualScoreDAO(IndividualScoreDAOImpl individualScoreDAO) {
        this.individualScoreDAO = individualScoreDAO;
    }

    public void insertIndividualScore(IndividualScores individualScores){
        individualScoreDAO.insertIndividualScore(individualScores);
    }

    public List getLatestTopScores(int numOfUsers, int numberOfCounts){
        return individualScoreDAO.getLatestTopScores(numOfUsers, numberOfCounts);
    }

    public IndividualScores getLatestIndividualScore(int empId){
        return individualScoreDAO.getLatestIndividualScore(empId);
    }

    public List getIndividualScores(int empId){
        return individualScoreDAO.getIndividualScores(empId);
    }
}

