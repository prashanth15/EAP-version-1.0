package com.app.Criteria.handler;

import com.app.Criteria.individual.*;
import com.app.model.epa.IndividualScoreWeight;
import com.app.model.epa.IndividualScores;
import com.app.service.epa.IndiScoreWeightService;
import com.app.service.epa.IndividualScoreServiceImpl;
import com.app.service.epa.SprintServiceImpl;
import com.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class IndividualScoreHandler {

    @Autowired
    private WorkCompletionOnTime workCompletionOnTime;

    @Autowired
    private PlanningTheProject planningTheProject;

    @Autowired
    private WorkEfficiency workEfficiency;

    @Autowired
    private DefectsCount defectsCount;

    @Autowired
    private FixedDefectsCount fixedDefectsCount;

    @Autowired
    private CodeQualityIssues codeQualityIssues;

    @Autowired
    private CodeQualityIssuesFixed codeQualityIssuesFixed;

    @Autowired
    private PersonalTraitsScore personalTraitsScore;

    @Autowired
    private Attendance attendance;

    @Autowired
    private IndiScoreWeightService indiScoreWeightService;

    @Autowired
    @Qualifier(value = "sprintServiceImpl")
    private SprintServiceImpl sprintService;

    @Autowired
    @Qualifier("indiScore")
    private IndividualScoreServiceImpl individualScoreService;

    @Autowired
    private ProjectContribution projectContribution;

    @Autowired
    Utils utils;

    public void updateIndividualScore(int emp_id){
        long attendanceDays = attendance.getAttendanceDays(emp_id);

        //get personal scores
        double workCompletion = workCompletionOnTime.getCriteriaScore(emp_id);
        double planningProject = planningTheProject.getCriteriaScore(emp_id);
        double efficiency = workEfficiency.getCriteriaScore(emp_id);
        double defectCount = defectsCount.getCriteriaScore(emp_id);
        double fixedDefectCount = fixedDefectsCount.getCriteriaScore(emp_id);
        double codeQuality = codeQualityIssues.getCriteriaScore(emp_id);
        double codeQualityFixed = codeQualityIssuesFixed.getCriteriaScore(emp_id);
        double personalTraits = personalTraitsScore.getScore(emp_id);
        double projectTeamContribution = projectContribution.getCriteriaScore(emp_id);

        //get current time
        Date currentTime = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updatedAt = dateFormat.format(currentTime);

        //get individual score ratios
        IndividualScoreWeight individualScoreWeight = indiScoreWeightService.getIndividualScore(emp_id);

        if(individualScoreWeight != null){
            int sprintDuration = sprintService.getSprintDuration();
            double actualScore = 0;
            //calculate summation of each score
            double totalScore = (workCompletion * individualScoreWeight.getWork_completion())/100 +
                    (planningProject * individualScoreWeight.getPlanning_the_project())/100 +
                    (efficiency * individualScoreWeight.getWork_efficiency())/100 +
                    (defectCount * individualScoreWeight.getDefects_count())/100 +
                    (fixedDefectCount * individualScoreWeight.getFixed_defects_count())/100 +
                    (codeQuality * individualScoreWeight.getCode_quality_issue())/100 +
                    (codeQualityFixed * individualScoreWeight.getCqi_fixed())/100 +
                    (personalTraits * individualScoreWeight.getPersonal_traits())/100 +
                    (projectTeamContribution * individualScoreWeight.getProject_team_contribution())/100;

            if(attendanceDays != 0) {
                actualScore = (totalScore / attendanceDays) * sprintDuration;
            }
            //prepare score object
            IndividualScores individualScores = new IndividualScores();

            individualScores.setEmp_id(emp_id);

            individualScores.setWork_completion(utils.round(workCompletion, 2));
            individualScores.setPlanning_the_project(utils.round(planningProject, 2));
            individualScores.setWork_efficiency(utils.round(efficiency, 2));
            individualScores.setDefects_count(utils.round(defectCount, 2));
            individualScores.setFixed_defects_count(utils.round(fixedDefectCount, 2));
            individualScores.setCode_quality_issue(utils.round(codeQuality, 2));
            individualScores.setCqi_fixed(utils.round(codeQualityFixed, 2));
            individualScores.setPersonal_traits(utils.round(personalTraits, 2));
            individualScores.setProject_team_contribution(utils.round(projectTeamContribution, 2));

            individualScores.setUpdated_at(updatedAt);
            individualScores.setTotal_score(utils.round(actualScore, 2));

            individualScoreService.insertIndividualScore(individualScores);
        }
    }
}
