package com.app.service.openProject;

import com.app.dao.interfaces.openProject.UsersOpenProjectDAO;
import com.app.model.openProject.UsersOpenProject;
import com.app.service.interfaces.openProject.UserOpenProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserOpenProjectServiceImpl implements UserOpenProjectService {

    UsersOpenProjectDAO usersOpenProjectDAO;


    @Autowired
    public void setUsersOpenProjectDAO(@Qualifier("UserOpenProjectDAO")UsersOpenProjectDAO usersOpenProjectDAO){
        this.usersOpenProjectDAO = usersOpenProjectDAO;
    }


    @Override
    public List<Object[]> listUsers() {
        return usersOpenProjectDAO.listUsers();
    }

    public String getLoginByUserId(int id){
        return usersOpenProjectDAO.getUserLoginById(id);
    }

    public String getFirstName(int id){
        return usersOpenProjectDAO.getFirstName(id);
    }

}
