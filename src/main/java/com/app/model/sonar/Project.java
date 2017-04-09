package com.app.model.sonar;

import javax.persistence.*;

@Entity
@Table(name="projects")
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String project_uuid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject_uuid() {
        return project_uuid;
    }

    public void setProject_uuid(String project_uuid) {
        this.project_uuid = project_uuid;
    }
}
