package com.app.model.epa;

import javax.persistence.*;

@Entity
@Table(name="project_scores")
public class ProjectScores {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int project_id;
    private double team_work_completion;
    private double team_efficiency;
    private double team_code_quality;
    private double team_defects_count;
    private String updated_at;
    private double total_score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public double getTeam_work_completion() {
        return team_work_completion;
    }

    public void setTeam_work_completion(double team_work_completion) {
        this.team_work_completion = team_work_completion;
    }

    public double getTeam_efficiency() {
        return team_efficiency;
    }

    public void setTeam_efficiency(double team_efficiency) {
        this.team_efficiency = team_efficiency;
    }

    public double getTeam_code_quality() {
        return team_code_quality;
    }

    public void setTeam_code_quality(double team_code_quality) {
        this.team_code_quality = team_code_quality;
    }

    public double getTeam_defects_count() {
        return team_defects_count;
    }

    public void setTeam_defects_count(double team_defects_count) {
        this.team_defects_count = team_defects_count;
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
