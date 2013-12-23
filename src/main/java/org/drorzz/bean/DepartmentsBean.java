package org.drorzz.bean;

import org.drorzz.model.Department;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;
import org.drorzz.dao.DepartmentDAO;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 17.12.13
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name="departmentsBean")
@ViewScoped
public class DepartmentsBean {
    @ManagedProperty("#{departmentDAO}")
    private DepartmentDAO departmentDAO;

    public List<Department> getAllDepartments(){
        return departmentDAO.getAll();
    }

    public DepartmentDAO getDepartmentDAO() {
        return departmentDAO;
    }

    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }
}
