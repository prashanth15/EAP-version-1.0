package com.app.service.epa;

import com.app.dao.epa.IndividualScoreWeightDAOImpl;
import com.app.model.epa.IndividualScoreWeight;
import com.app.requestBody.IndiScoreWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndiScoreWeightService {
    IndividualScoreWeightDAOImpl individualScoreWeightDAO;

    @Autowired
    public void setIndividualScoreWeightDAO(IndividualScoreWeightDAOImpl individualScoreWeightDAO) {
        this.individualScoreWeightDAO = individualScoreWeightDAO;
    }

    public IndividualScoreWeight getIndividualScore(int empId){
        return individualScoreWeightDAO.getIndividualScore(empId);
    }


    public void updateScore(IndiScoreWeight indiScoreWeight){

        if(indiScoreWeight != null) {
            IndividualScoreWeight individualScoreWeight = new IndividualScoreWeight();

            individualScoreWeight.setEmp_id(indiScoreWeight.getEmp_id());
            individualScoreWeight.setCode_quality_issue(indiScoreWeight.getCode_quality_issue());
            individualScoreWeight.setCqi_fixed(indiScoreWeight.getCqi_fixed());
            individualScoreWeight.setDefects_count(indiScoreWeight.getDefects_count());
            individualScoreWeight.setFixed_defects_count(indiScoreWeight.getFixed_defects_count());
            individualScoreWeight.setPersonal_traits(indiScoreWeight.getPersonal_traits());
            individualScoreWeight.setPlanning_the_project(indiScoreWeight.getPlanning_the_project());
            individualScoreWeight.setProject_team_contribution(indiScoreWeight.getProject_team_contribution());
            individualScoreWeight.setWork_completion(indiScoreWeight.getWork_completion());
            individualScoreWeight.setWork_efficiency(indiScoreWeight.getWork_efficiency());

            individualScoreWeightDAO.updateScore(individualScoreWeight);
        }
    }
}
