package org.drorzz.bean;



import org.drorzz.model.DayReport;

import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class DayReportBean {
    @Autowired
    private DayReportDAO dayReportDAO;

    public List<DayReport> getAllDayReports(){
        return dayReportDAO.getAll();
    }

}
