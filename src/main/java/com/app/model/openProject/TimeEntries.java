package com.app.model.openProject;

import javax.persistence.*;

@Entity
@Table(name="time_entries")
public class TimeEntries {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int project_id;
    private int user_id;
    private int work_package_id;
    private float hours;
    private String comments;
    private int activity_id;
    private String spent_on;
    private int tyear;
    private int tmonth;
    private int tweek;
    private String created_on;
    private String updated_on;

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getWork_package_id() {
        return work_package_id;
    }

    public void setWork_package_id(int work_package_id) {
        this.work_package_id = work_package_id;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public String getSpent_on() {
        return spent_on;
    }

    public void setSpent_on(String spent_on) {
        this.spent_on = spent_on;
    }

    public int getTyear() {
        return tyear;
    }

    public void setTyear(int tyear) {
        this.tyear = tyear;
    }

    public int getTmonth() {
        return tmonth;
    }

    public void setTmonth(int tmonth) {
        this.tmonth = tmonth;
    }

    public int getTweek() {
        return tweek;
    }

    public void setTweek(int tweek) {
        this.tweek = tweek;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }
}
