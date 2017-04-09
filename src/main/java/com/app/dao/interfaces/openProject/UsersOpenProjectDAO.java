package com.app.dao.interfaces.openProject;

import com.app.model.openProject.UsersOpenProject;

import java.util.List;


public interface UsersOpenProjectDAO {
    public List<Object[]> listUsers();
    public String getUserLoginById(int id);
    public String getFirstName(int id);

}
