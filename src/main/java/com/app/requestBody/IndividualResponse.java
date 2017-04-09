package com.app.requestBody;

import java.util.ArrayList;

public class IndividualResponse {
    private double totalScore;
    private ArrayList criteriaIndividual;
    private ArrayList lineChartData;

    private double projectTotalScore;
    private ArrayList projectCriteria;
    private ArrayList projectLineChartData;

    public double getProjectTotalScore() {
        return projectTotalScore;
    }

    public void setProjectTotalScore(double projectTotalScore) {
        this.projectTotalScore = projectTotalScore;
    }

    public ArrayList getProjectCriteria() {
        return projectCriteria;
    }

    public void setProjectCriteria(ArrayList projectCriteria) {
        this.projectCriteria = projectCriteria;
    }

    public ArrayList getProjectLineChartData() {
        return projectLineChartData;
    }

    public void setProjectLineChartData(ArrayList projectLineChartData) {
        this.projectLineChartData = projectLineChartData;
    }

    public ArrayList getLineChartData() {
        return lineChartData;
    }

    public void setLineChartData(ArrayList lineChartData) {
        this.lineChartData = lineChartData;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public ArrayList getCriteriaIndividual() {
        return criteriaIndividual;
    }

    public void setCriteriaIndividual(ArrayList criteriaIndividual) {
        this.criteriaIndividual = criteriaIndividual;
    }
}
