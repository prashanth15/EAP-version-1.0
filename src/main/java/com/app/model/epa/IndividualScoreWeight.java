package com.app.model.epa;

import javax.persistence.*;

@Entity
@Table(name="individual_score_weightage")
public class IndividualScoreWeight {
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;

    private int work_completion;
    private int planning_the_project;
    private int work_efficiency;
    private int defects_count;
    private int fixed_defects_count;
    private int code_quality_issue;
    private int cqi_fixed;
    private int project_team_contribution;
    private int personal_traits;

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getWork_completion() {
        return work_completion;
    }

    public void setWork_completion(int work_completion) {
        this.work_completion = work_completion;
    }

    public int getPlanning_the_project() {
        return planning_the_project;
    }

    public void setPlanning_the_project(int planning_the_project) {
        this.planning_the_project = planning_the_project;
    }

    public int getWork_efficiency() {
        return work_efficiency;
    }

    public void setWork_efficiency(int work_efficiency) {
        this.work_efficiency = work_efficiency;
    }

    public int getDefects_count() {
        return defects_count;
    }

    public void setDefects_count(int defects_count) {
        this.defects_count = defects_count;
    }

    public int getFixed_defects_count() {
        return fixed_defects_count;
    }

    public void setFixed_defects_count(int fixed_defects_count) {
        this.fixed_defects_count = fixed_defects_count;
    }

    public int getCode_quality_issue() {
        return code_quality_issue;
    }

    public void setCode_quality_issue(int code_quality_issue) {
        this.code_quality_issue = code_quality_issue;
    }

    public int getCqi_fixed() {
        return cqi_fixed;
    }

    public void setCqi_fixed(int cqi_fixed) {
        this.cqi_fixed = cqi_fixed;
    }

    public int getProject_team_contribution() {
        return project_team_contribution;
    }

    public void setProject_team_contribution(int project_team_contribution) {
        this.project_team_contribution = project_team_contribution;
    }

    public int getPersonal_traits() {
        return personal_traits;
    }

    public void setPersonal_traits(int personal_traits) {
        this.personal_traits = personal_traits;
    }
}
