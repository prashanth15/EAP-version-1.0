package com.app.dao.openProject;

import com.app.dao.interfaces.openProject.WorkPackagesDAO;
import com.app.model.openProject.PlanningDataModel;
import com.app.model.openProject.WorkPackages;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, value = "transactionManager_openproject")
public class WorkPackagesDAOImpl implements WorkPackagesDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("hibernate4_openproject") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<WorkPackages> getStatusOfTasks(int userId, String startDate, String endDate) {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "FROM com.app.model.openProject.WorkPackages as wp WHERE wp.assigned_to_id = " + userId + " AND wp.type_id = 1 AND wp.updated_at between '" + startDate + "' and '" + endDate + "'";
        List<WorkPackages> data = session.createQuery(query).list();

        return data;
    }

    @SuppressWarnings("unchecked")
    public PlanningDataModel getPlaningTheProject(int userId, String startDate, String endDate) {
        Session session = this.sessionFactory.getCurrentSession();

        /*
        Query : return estimated hour, and actual hour from work_packages and time estimate
         */
        String query = "SELECT wpack.estimated_hours, SUM(te.hours) FROM " +
                "com.app.model.openProject.WorkPackages AS wpack, com.app.model.openProject.TimeEntries as te WHERE " +
                "wpack.assigned_to_id = te.user_id AND wpack.id = te.work_package_id AND wpack.project_id = te.project_id " +
                "AND te.hours IS NOT NULL AND wpack.assigned_to_id = " + userId + " AND wpack.assigned_to_id = te.user_id " +
                "AND wpack.type_id = 1 AND wpack.estimated_hours IS NOT NULL AND wpack.updated_at between '" + startDate +
                "' and '" + endDate + "' GROUP BY wpack.estimated_hours";

        final List<Object []> data = session.createQuery(query).list();

        Double actualSum = 0.0D;
        Float estimate = 0.0f;
        for(Object [] obj : data){
            estimate += (Float) obj[0];
            actualSum += (Double) obj[1];
        }
        PlanningDataModel planningDataModel = new PlanningDataModel();
        planningDataModel.setEstimated_hours(estimate);
        planningDataModel.setHours(actualSum);

        return planningDataModel;
    }


    @SuppressWarnings("unchecked")
    public long getDefectCount(int userId, String startDate, String endDate) {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT count(wp.id) FROM com.app.model.openProject.WorkPackages as wp WHERE wp.author_id = " +
                userId + " AND wp.updated_at between '" + startDate + "' and '" + endDate + "' AND wp.type_id = 7 AND " +
                "wp.status_id IS NOT 15";

        Long count = (Long)session.createQuery(query).uniqueResult();

        if(count == 0L){
            String check = "SELECT count(wp.id) FROM com.app.model.openProject.WorkPackages as wp WHERE wp.author_id = " +
                    userId + " OR wp.assigned_to_id = " + userId + " AND wp.updated_at between '" + startDate + "' and '" + endDate + "' AND wp.type_id != 7";
            Long checkCount = (Long)session.createQuery(check).uniqueResult();

            if(checkCount == 0L){
                count = -1L;
            }
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    public long getFixedDefectCount(int userId, String startDate, String endDate) {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT count(wp.id) FROM com.app.model.openProject.WorkPackages as wp WHERE wp.assigned_to_id = " +
                userId + " AND wp.updated_at between '" + startDate + "' and '" + endDate + "' AND wp.type_id = 7 AND " +
                "wp.status_id = 13";

        Long count = (Long)session.createQuery(query).uniqueResult();

        if(count == 0){
            String check = "SELECT count(wp.id) FROM com.app.model.openProject.WorkPackages as wp WHERE wp.assigned_to_id = " +
                    userId + " OR wp.author_id = " + userId + " AND wp.updated_at between '" + startDate + "' and '" + endDate + "' AND wp.type_id != 7";
            Long checkCount = (Long)session.createQuery(check).uniqueResult();

            if(checkCount == 0L){
                count = -1L;
            }
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    public List<WorkPackages> getStatusOfTasksProjects(int projectId, String startDate, String endDate) {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "FROM com.app.model.openProject.WorkPackages as wp WHERE wp.project_id = " + projectId + " AND wp.type_id = 1 AND wp.updated_at between '" + startDate + "' and '" + endDate + "'";
        List<WorkPackages> data = session.createQuery(query).list();

        return data;
    }


    @SuppressWarnings("unchecked")
    public PlanningDataModel getPlaningTheProjectTeam(int projectId, String startDate, String endDate) {
        Session session = this.sessionFactory.getCurrentSession();

        /*
        Query : return estimated hour, and actual hour from work_packages and time estimate
         */
        String query = "SELECT wpack.estimated_hours, SUM(te.hours) FROM " +
                "com.app.model.openProject.WorkPackages AS wpack, com.app.model.openProject.TimeEntries as te WHERE " +
                "wpack.project_id = te.project_id AND wpack.id = te.work_package_id AND wpack.project_id = te.project_id " +
                "AND te.hours IS NOT NULL AND wpack.project_id = " + projectId + " AND wpack.project_id = te.project_id " +
                "AND wpack.type_id = 1 AND wpack.estimated_hours IS NOT NULL AND wpack.updated_at between '" + startDate +
                "' and '" + endDate + "' GROUP BY wpack.estimated_hours";

        final List<Object []> data = session.createQuery(query).list();

        Double actualSum = 0.0D;
        Float estimate = 0.0f;
        for(Object [] obj : data){
            estimate += (Float) obj[0];
            actualSum += (Double) obj[1];
        }
        PlanningDataModel planningDataModel = new PlanningDataModel();
        planningDataModel.setEstimated_hours(estimate);
        planningDataModel.setHours(actualSum);

        return planningDataModel;
    }

    @SuppressWarnings("unchecked")
    public long getProjectDefectCount(int projectId, String startDate, String endDate) {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT count(wp.id) FROM com.app.model.openProject.WorkPackages as wp WHERE wp.project_id = " +
                projectId + " AND wp.updated_at between '" + startDate + "' and '" + endDate + "' AND wp.type_id = 7 AND " +
                "wp.status_id IS NOT 15";

        Long count = (Long)session.createQuery(query).uniqueResult();

        if(count == 0L){
            String check = "SELECT count(wp.id) FROM com.app.model.openProject.WorkPackages as wp WHERE wp.project_id = " +
                    projectId + " AND wp.updated_at between '" + startDate + "' and '" + endDate + "' AND wp.type_id != 7 AND " +
                    "wp.status_id IS NOT 15";
            Long checkCount = (Long)session.createQuery(check).uniqueResult();

            if(checkCount == 0L){
                count = -1L;
            }
        }
        return count;
    }

    public int getProjectIdForEmployee(int empId, String startDate, String endDate){
        int pId = 0;

        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT wp.project_id FROM com.app.model.openProject.WorkPackages as wp WHERE wp.assigned_to_id = " + empId +
                " AND wp.updated_at between '" + startDate + "' and '" + endDate + "' GROUP BY wp.project_id ORDER BY COUNT(wp.project_id) DESC";
        Integer projectId = (Integer)session.createQuery(query).uniqueResult();
        if(projectId != null){
            pId = projectId;
        }
        return  pId;
    }

    public int getProjectIdForEmployee(int empId){
        int pId = 0;

        Session session = this.sessionFactory.getCurrentSession();
        String query = "SELECT wp.project_id FROM work_packages as wp WHERE wp.assigned_to_id = " + empId +
                " ORDER BY wp.updated_at DESC LIMIT 1";
        Integer projectId = (Integer)session.createSQLQuery(query).uniqueResult();
        if(projectId != null){
            pId = projectId;
        }
        return  pId;
    }

}
