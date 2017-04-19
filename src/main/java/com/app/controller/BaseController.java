package com.app.controller;

import com.app.Criteria.handler.ScoreHandler;
import com.app.Criteria.projects.ProjectTeamDefectCount;
import com.app.SchedularUtils.TaskScheduler;
import com.app.model.epa.Sprint;
import com.app.requestBody.*;
import com.app.service.controllerServices.DataServiceForVisualization;
import com.app.service.controllerServices.IndividualPageService;
import com.app.service.epa.IndiScoreWeightService;
import com.app.service.epa.IndividualScoreServiceImpl;
import com.app.service.epa.PersonalTraitsServiceImpl;
import com.app.service.epa.SprintServiceImpl;
import com.app.service.openProject.UserOpenProjectServiceImpl;
import com.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class BaseController {

    @Autowired(required=true)
    @Qualifier(value="userOpenProjectService")
    UserOpenProjectServiceImpl userOpenProjectService;

    @Autowired
    @Qualifier(value = "sprintServiceImpl")
    SprintServiceImpl sprintService;

    @Autowired
    PersonalTraitsServiceImpl personalTraitsServiceImpl;

    @Autowired
    @Qualifier(value = "indiScoreWeightService")
    IndiScoreWeightService indiScoreWeightService;

    @Autowired
    DataServiceForVisualization dataServiceForVisualization;

    @Autowired
    IndividualPageService individualPageService;

    @Autowired
    TaskScheduler taskScheduler;

    @Autowired
    Utils utils;

    @RequestMapping(value = "/configuration", method = RequestMethod.GET)
    public String goToLoginPage(ModelMap model) {
        model.addAttribute("users", userOpenProjectService.listUsers());
        return "adminConfiguration";
    }

    @RequestMapping(value = "/individual", method = RequestMethod.GET)
    public String goToIndividualPage(ModelMap model) {
        model.addAttribute("users", userOpenProjectService.listUsers());
        return "individual";
    }

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public String goToSample(ModelMap model) {
        return "theme";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String goToAdminPage(ModelMap model) {
        model.addAttribute("users", userOpenProjectService.listUsers());
        dataServiceForVisualization.setMaxPerformers(model);
        dataServiceForVisualization.setMaxPerformProjects(model);
        return "admin";
    }

    @RequestMapping(value = "/update_duration", method = RequestMethod.POST, consumes="application/json",headers = "content-type=application/json")
    public @ResponseBody SprintDuration updateDuration(@RequestBody SprintDuration sprintDuration) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Sprint sprint = new Sprint();
        sprint.setId(1);
        sprint.setLast_update(simpleDateFormat.format(date));

        int duration = Integer.parseInt(sprintDuration.getDuration());
        Date startDate = sprintDuration.getStartDate();

        sprint.setNo_of_days(duration);
        sprintService.updateSprint(sprint);

        taskScheduler.scheduleTask(startDate, utils.springDurationCheck(duration, startDate));

        return sprintDuration;
    }

    @RequestMapping(value = "/update_personal_traits", method = RequestMethod.POST, consumes="application/json",headers = "content-type=application/json")
    public @ResponseBody PersonalTraitsRB updatePerTraits(@RequestBody PersonalTraitsRB personalTraitsRB) {
        personalTraitsServiceImpl.updatePersonalTrait(personalTraitsRB.getEmpId(), personalTraitsRB.getScore());
        return personalTraitsRB;
    }


    @RequestMapping(value = "/update_isw", method = RequestMethod.POST, consumes="application/json",headers = "content-type=application/json")
    public @ResponseBody IndiScoreWeight updateIndiScoreWeights(@RequestBody IndiScoreWeight indiScoreWeight) {
        indiScoreWeightService.updateScore(indiScoreWeight);
        return indiScoreWeight;
    }

    @RequestMapping(value = "/load_individual_page", method = RequestMethod.POST, consumes="application/json",headers = "content-type=application/json")
    public @ResponseBody IndividualResponse getIndividualData(@RequestBody Individual individual) {
        IndividualResponse individualResponse = new IndividualResponse();
        individualPageService.loadData(individual.getEmpId(), individualResponse);
        return individualResponse;
    }

}
