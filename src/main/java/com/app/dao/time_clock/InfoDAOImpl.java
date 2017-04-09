package com.app.dao.time_clock;

import com.app.dao.interfaces.time_clock.InfoDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_time_clock")
public class InfoDAOImpl implements InfoDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_time_clock") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long getAttendance(String employeeName, String startDate, String endDate){
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT COUNT(DISTINCT info.timestamp) FROM com.app.model.time_clock.Info as info WHERE info.fullname = '" + employeeName +
                "' AND info.timestamp BETWEEN '" + startDate + "' AND '" + endDate + "' AND info.inout != 'Casual'";
        Long attendance = (Long) session.createQuery(query).uniqueResult();

        return attendance;
    }
}
