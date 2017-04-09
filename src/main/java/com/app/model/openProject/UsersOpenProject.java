package com.app.model.openProject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class UsersOpenProject {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    private String firstname;
    private String lastname;
    private String mail;
    private String admin;
    private int status;
    private Date last_login_on;
    private String language;
    private String auth_source_id;
    private String created_on;
    private String updated_on;
    private String type;
    private String identity_url;
//    private String mail_notification;
//    private String first_login;
//    private String force_password_change;
//    private String failed_login_count;
//    private String last_failed_login_on;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Date getLast_login_on() {
        return last_login_on;
    }

    public void setLast_login_on(Date last_login_on) {
        this.last_login_on = last_login_on;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuth_source_id() {
        return auth_source_id;
    }

    public void setAuth_source_id(String auth_source_id) {
        this.auth_source_id = auth_source_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentity_url() {
        return identity_url;
    }

    public void setIdentity_url(String identity_url) {
        this.identity_url = identity_url;
    }
//
//    public String getMail_notification() {
//        return mail_notification;
//    }
//
//    public void setMail_notification(String mail_notification) {
//        this.mail_notification = mail_notification;
//    }
//
//    public String getFirst_login() {
//        return first_login;
//    }
//
//    public void setFirst_login(String first_login) {
//        this.first_login = first_login;
//    }
//
//    public String getForce_password_change() {
//        return force_password_change;
//    }
//
//    public void setForce_password_change(String force_password_change) {
//        this.force_password_change = force_password_change;
//    }
//
//    public String getFailed_login_count() {
//        return failed_login_count;
//    }
//
//    public void setFailed_login_count(String failed_login_count) {
//        this.failed_login_count = failed_login_count;
//    }
//
//    public String getLast_failed_login_on() {
//        return last_failed_login_on;
//    }
//
//    public void setLast_failed_login_on(String last_failed_login_on) {
//        this.last_failed_login_on = last_failed_login_on;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
