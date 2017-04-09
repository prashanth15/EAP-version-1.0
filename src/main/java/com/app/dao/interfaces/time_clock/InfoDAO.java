package com.app.dao.interfaces.time_clock;

public interface InfoDAO {
    public long getAttendance(String employeeName, String startDate, String endDate);
}
