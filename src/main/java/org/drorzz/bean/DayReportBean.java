package org.drorzz.bean;



import org.drorzz.model.DayReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;
import java.util.List;
import org.drorzz.dao.DayReportDAO;
/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 18.12.13
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@Scope(value = "session")
public class DayReportBean {
    @Autowired
    private DayReportDAO dayReportDAO;

    public List<DayReport> getAllDayReports(){
        return dayReportDAO.getAll();
    }

}
