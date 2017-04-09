package com.app.model.sonar;

import javax.persistence.*;

@Entity
@Table(name="issues")
public class Issues {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String project_uuid;
    private String severity;
    private String status;
    private String author_login;
    private String created_at;
    private String updated_at;
    private String assignee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject_uuid() {
        return project_uuid;
    }

    public void setProject_uuid(String project_uuid) {
        this.project_uuid = project_uuid;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor_login() {
        return author_login;
    }

    public void setAuthor_login(String author_login) {
        this.author_login = author_login;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
