package com.app.dao.interfaces.epa;

import com.app.model.epa.Sprint;


public interface SprintDAO {
    public void addSprint(Sprint sprint);
    public void updateSprint(Sprint sprint);
    public int getSprintDuration();
}
