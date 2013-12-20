package org.drorzz.bean;

import org.drorzz.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;
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
@ManagedBean
@ViewScoped
public class DepartmentsBean {
    @Autowired
    private DepartmentDAO departmentDAO;

    public List<Department> getAllDepartments(){
        return departmentDAO.getAll();
    }
}
