package com.app.model.epa;


import javax.persistence.*;

@Entity
@Table(name="individualscores")
public class IndividualScores {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int emp_id;
    private double work_completion;
    private double planning_the_project;
    private double work_efficiency;
    private double defects_count;
    private double fixed_defects_count;
    private double code_quality_issue;
    private double cqi_fixed;
    private double project_team_contribution;
    private double personal_traits;
    private String updated_at;
    private double total_score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public double getWork_completion() {
        return work_completion;
    }

    public void setWork_completion(double work_completion) {
        this.work_completion = work_completion;
    }

    public double getPlanning_the_project() {
        return planning_the_project;
    }

    public void setPlanning_the_project(double planning_the_project) {
        this.planning_the_project = planning_the_project;
    }

    public double getWork_efficiency() {
        return work_efficiency;
    }

    public void setWork_efficiency(double work_efficiency) {
        this.work_efficiency = work_efficiency;
    }

    public double getDefects_count() {
        return defects_count;
    }

    public void setDefects_count(double defects_count) {
        this.defects_count = defects_count;
    }

    public double getFixed_defects_count() {
        return fixed_defects_count;
    }

    public void setFixed_defects_count(double fixed_defects_count) {
        this.fixed_defects_count = fixed_defects_count;
    }

    public double getCode_quality_issue() {
        return code_quality_issue;
    }

    public void setCode_quality_issue(double code_quality_issue) {
        this.code_quality_issue = code_quality_issue;
    }

    public double getCqi_fixed() {
        return cqi_fixed;
    }

    public void setCqi_fixed(double cqi_fixed) {
        this.cqi_fixed = cqi_fixed;
    }

    public double getProject_team_contribution() {
        return project_team_contribution;
    }

    public void setProject_team_contribution(double project_team_contribution) {
        this.project_team_contribution = project_team_contribution;
    }

    public double getPersonal_traits() {
        return personal_traits;
    }

    public void setPersonal_traits(double personal_traits) {
        this.personal_traits = personal_traits;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public double getTotal_score() {
        return total_score;
    }

    public void setTotal_score(double total_score) {
        this.total_score = total_score;
    }
}
