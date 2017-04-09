package com.app.service.time_clock;

import com.app.dao.interfaces.time_clock.InfoDAO;
import com.app.service.interfaces.time_clock.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    InfoDAO infoDAO;

    @Autowired
    public void setInfoDAO(@Qualifier("InfoDAO") InfoDAO infoDAO) {
        this.infoDAO = infoDAO;
    }

    @Override
    public long getAttendance(String employeeName, String startDate, String endDate){
        return infoDAO.getAttendance(employeeName, startDate, endDate);
    }
}
