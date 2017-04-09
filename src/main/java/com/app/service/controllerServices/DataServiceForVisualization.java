package com.app.service.controllerServices;

import com.app.model.openProject.Projects;
import com.app.service.epa.IndividualScoreServiceImpl;
import com.app.service.epa.ProjectScoreServiceImpl;
import com.app.service.interfaces.openProject.ProjectsService;
import com.app.service.interfaces.openProject.UserOpenProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class DataServiceForVisualization {
    private static final int BEST_EMPLOYEES_COUNT = 5;
    private static final int BEST_PROJECT_COUNT = 5;

    @Autowired
    UserOpenProjectService userOpenProjectService;

    @Autowired
    ProjectsService projectsService;

    @Autowired
    @Qualifier("proScore")
    ProjectScoreServiceImpl projectScoreService;

    @Autowired
    @Qualifier("indiScore")
    IndividualScoreServiceImpl individualScoreService;

    public void setMaxPerformers(ModelMap model) {
        List users = userOpenProjectService.listUsers();
        StringBuilder data = new StringBuilder();
        data.append("[[\"Element\", \"Score\", { role: \"style\" } ],");
        LinkedHashMap<String, Double> employeeMap = new LinkedHashMap<>();
        if (users != null && !users.isEmpty()) {
            int numberOfUsers = users.size();
            List<Object[]> bestEmployee = individualScoreService.getLatestTopScores(numberOfUsers, BEST_EMPLOYEES_COUNT);

            if (bestEmployee != null && !bestEmployee.isEmpty()) {
                for (Object[] emp : bestEmployee) {
                    int empId = (int) emp[0];
                    double score = (double) emp[1];
                    String name = userOpenProjectService.getFirstName(empId);
                    data.append("[");
                    data.append("\"" + name + "\", " + score + ", \"#14b3ff\"");
                    data.append("],");
                    employeeMap.put(name, score);
                }
            }
        }
        data.append("]");
        model.addAttribute("bestPerformers", employeeMap);
        model.addAttribute("bestPerformersData", data.toString());
    }

    public void setMaxPerformProjects(ModelMap model) {
        List<Projects> projects = projectsService.getProjects();
        StringBuilder data = new StringBuilder();
        data.append("[[\"Element\", \"Score\", { role: \"style\" } ],");
        LinkedHashMap<String, Double> employeeMap = new LinkedHashMap<>();

        if (projects != null && !projects.isEmpty()) {
            int numberOfProjects = projects.size();
            List<Object[]> bestProjectsList = projectScoreService.getLatestTopScores(numberOfProjects, BEST_PROJECT_COUNT);

            if (bestProjectsList != null && !bestProjectsList.isEmpty()) {
                for (Object[] project : bestProjectsList) {
                    int projectId = (int) project[0];
                    double score = (double) project[1];
                    String name = projectsService.getProjectNameById(projectId);
                    data.append("[");
                    data.append("\"" + name + "\", " + score + ", \"#14ffd4\"");
                    data.append("],");
                    employeeMap.put(name, score);
                }
            }
        }
        data.append("]");
        model.addAttribute("bestProjects", employeeMap);
        model.addAttribute("bestProjectsData", data.toString());
    }
}
