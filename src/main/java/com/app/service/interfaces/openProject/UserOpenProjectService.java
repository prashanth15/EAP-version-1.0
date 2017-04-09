package com.app.service.interfaces.openProject;

import com.app.model.openProject.UsersOpenProject;

import java.util.List;


public interface UserOpenProjectService {
    public List<Object[]> listUsers();
    public String getLoginByUserId(int id);
    public String getFirstName(int id);
}
