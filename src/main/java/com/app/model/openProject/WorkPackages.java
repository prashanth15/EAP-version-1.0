package com.app.model.openProject;

import javax.persistence.*;


@Entity
@Table(name="work_packages")
public class WorkPackages {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer type_id;
    private Integer assigned_to_id;
    private Integer project_id;
    private Integer status_id;
    private String updated_at;
    private Float estimated_hours;
    private Integer author_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getAssigned_to_id() {
        return assigned_to_id;
    }

    public void setAssigned_to_id(Integer assigned_to_id) {
        this.assigned_to_id = assigned_to_id;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Float getEstimated_hours() {
        return estimated_hours;
    }

    public void setEstimated_hours(Float estimated_hours) {
        this.estimated_hours = estimated_hours;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }
}
