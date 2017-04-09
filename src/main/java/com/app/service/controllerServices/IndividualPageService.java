package com.app.service.controllerServices;

import com.app.model.epa.IndividualScoreWeight;
import com.app.model.epa.IndividualScores;
import com.app.requestBody.IndividualResponse;
import com.app.service.epa.IndiScoreWeightService;
import com.app.service.epa.IndividualScoreServiceImpl;
import com.app.service.epa.ProjectScoreServiceImpl;
import com.app.service.epa.SprintServiceImpl;
import com.app.service.openProject.WorkPackagesServiceImpl;
import com.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class IndividualPageService {

    @Autowired
    @Qualifier("indiScore")
    IndividualScoreServiceImpl individualScoreService;

    @Autowired
    IndiScoreWeightService indiScoreWeightService;

    @Autowired
    Utils utils;


    @Autowired
    @Qualifier(value = "sprintServiceImpl")
    SprintServiceImpl sprintService;

    @Autowired
    WorkPackagesServiceImpl workPackagesService;

    @Autowired
    @Qualifier("proScore")
    ProjectScoreServiceImpl projectScoreService;

    public void loadData(int empId, IndividualResponse individualResponse) {
        IndividualScores individualScores = individualScoreService.getLatestIndividualScore(empId);
        IndividualScoreWeight individualScoreWeight = indiScoreWeightService.getIndividualScore(empId);


        ArrayList criteriaData = new ArrayList();
        if (individualScores != null) {
            individualResponse.setTotalScore(individualScores.getTotal_score());
            if (individualScoreWeight != null) {
                criteriaData.add(new ArrayList(Arrays.asList("Criteria", "Expected", "Achieved")));
                if (individualScoreWeight.getWork_completion() != 0) {
                    criteriaData.add(new ArrayList(Arrays.asList("Work Completion", individualScoreWeight.getWork_completion(),
                            utils.round((individualScores.getWork_completion() / 100.0) * individualScoreWeight.getWork_completion(), 2))));
                }

                if (individualScoreWeight.getPlanning_the_project() != 0) {
                    criteriaData.add(new ArrayList(Arrays.asList("Project Planning", individualScoreWeight.getPlanning_the_project(),
                            utils.round((individualScores.getPlanning_the_project() / 100.0) * individualScoreWeight.getPlanning_the_project(), 2))));
                }

                if (individualScoreWeight.getWork_efficiency() != 0) {
                    criteriaData.add(new ArrayList(Arrays.asList("Work Efficiency", individualScoreWeight.getWork_efficiency(),
                            utils.round((individualScores.getWork_efficiency() / 100.0) * individualScoreWeight.getWork_efficiency(), 2))));
                }

                if (individualScoreWeight.getDefects_count() != 0) {
                    criteriaData.add(new ArrayList(Arrays.asList("Making Defects", individualScoreWeight.getDefects_count(),
                            utils.round((individualScores.getDefects_count() / 100.0) * individualScoreWeight.getDefects_count(), 2))));
                }

                if (individualScoreWeight.getFixed_defects_count() != 0) {
                    criteriaData.add(new ArrayList(Arrays.asList("Fixing Defects", individualScoreWeight.getFixed_defects_count(),
                            utils.round((individualScores.getFixed_defects_count() / 100.0) * individualScoreWeight.getFixed_defects_count(), 2))));
                }

                if (individualScoreWeight.getCode_quality_issue() != 0) {
                    criteriaData.add(new ArrayList(Arrays.asList("Code Quality", individualScoreWeight.getCode_quality_issue(),
                            utils.round((individualScores.getCode_quality_issue() / 100.0) * individualScoreWeight.getCode_quality_issue(), 2))));
                }

                if (individualScoreWeight.getCqi_fixed() != 0) {
                    criteriaData.add(new ArrayList(Arrays.asList("Fixing Code Quality", individualScoreWeight.getCqi_fixed(),
                            utils.round((individualScores.getCqi_fixed() / 100.0) * individualScoreWeight.getCqi_fixed(), 2))));
                }

                if (individualScoreWeight.getProject_team_contribution() != 0) {
                    criteriaData.add(new ArrayList(Arrays.asList("Project Team Contribution", individualScoreWeight.getProject_team_contribution(),
                            utils.round((individualScores.getProject_team_contribution() / 100.0) * individualScoreWeight.getProject_team_contribution(), 2))));
                }

                if (individualScoreWeight.getPersonal_traits() != 0) {
                    criteriaData.add(new ArrayList(Arrays.asList("Personal Traits", individualScoreWeight.getPersonal_traits(),
                            utils.round((individualScores.getPersonal_traits() / 100.0) * individualScoreWeight.getPersonal_traits(), 2))));
                }
            }
            individualResponse.setCriteriaIndividual(criteriaData);
            individualResponse.setLineChartData(getLineChartData(empId));
            setTeamProperties(empId, individualResponse);
        }
    }

    private ArrayList getLineChartData(int empId) {
        List<Double> scores = individualScoreService.getIndividualScores(empId);
        ArrayList<ArrayList> data = new ArrayList<>();
        if (scores != null && !scores.isEmpty()) {
            Double i = 1D;
            for (Double score : scores) {
                data.add(new ArrayList(Arrays.asList(i, score)));
                i++;
            }
        }
        return data;
    }


    private int getProjectId(int empId) {
        return workPackagesService.getProjectIdForEmployee(empId);
    }

    private void setTeamProperties(int empId, IndividualResponse individualResponse){
        int projectId = getProjectId(empId);
        Double totalScore = 0.0D;
        ArrayList criteriaData = new ArrayList();

        if(projectId != 0){
            List<Object[]> scoreList = projectScoreService.getLatestProjectScoreDetails(projectId);
            if(scoreList != null && !scoreList.isEmpty()){
                Object[] scoreArray = scoreList.get(0);
                totalScore = (double) scoreArray[7];
                criteriaData.add(new ArrayList(Arrays.asList("Criteria", "Expected", "Achieved")));
                if((double)scoreArray[2] != 0.0){
                    criteriaData.add(new ArrayList(Arrays.asList("Project work completion", 25,
                            utils.round(((double)scoreArray[2] / 100.0) * 25, 2))));
                }
                if((double)scoreArray[3] != 0.0){
                    criteriaData.add(new ArrayList(Arrays.asList("Project Efficiency", 25,
                            utils.round(((double)scoreArray[3] / 100.0) * 25, 2))));
                }
                if((double)scoreArray[4] != 0.0){
                    criteriaData.add(new ArrayList(Arrays.asList("Project Code Quality", 25,
                            utils.round(((double)scoreArray[4] / 100.0) * 25, 2))));
                }
                if((double)scoreArray[5] != 0.0){
                    criteriaData.add(new ArrayList(Arrays.asList("Project defects count", 25,
                            utils.round(((double)scoreArray[5] / 100.0) * 25, 2))));
                }
            }
        }
        individualResponse.setProjectTotalScore(totalScore);
        individualResponse.setProjectCriteria(criteriaData);
        individualResponse.setProjectLineChartData(getProjectChartData(projectId));
    }

    private ArrayList getProjectChartData(int projectId) {
        List<Double> scores = projectScoreService.getHistoryOfProjectScores(projectId);
        ArrayList<ArrayList> data = new ArrayList<>();
        if (scores != null && !scores.isEmpty()) {
            Double i = 1D;
            for (Double score : scores) {
                data.add(new ArrayList(Arrays.asList(i, score)));
                i++;
            }
        }
        return data;
    }

}

