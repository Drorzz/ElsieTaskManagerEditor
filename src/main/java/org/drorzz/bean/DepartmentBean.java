package org.drorzz.bean;

import org.drorzz.dao.DepartmentDAO;
import org.drorzz.model.Department;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Drorzz
 * Date: 20.12.13
 * Time: 0:39
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class DepartmentBean implements Serializable {
    private String departmentId;
    private Department department;

    @Autowired
    private DepartmentDAO departmentDAO;

    public void init(){
        if (departmentId == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }
        if (departmentId.trim().toLowerCase().equals("new")){
            try {
                department = departmentDAO.create();
            } catch (IllegalAccessException e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            } catch (InstantiationException e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }
        } else {
            department = departmentDAO.getById(Integer.valueOf(departmentId));
            if (department == null) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("department?id=new");
                } catch (IOException e) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
                }
            }
        }
    }

    public void saveDepartment(){
        departmentDAO.save(department);
    }

    public Department getDepartment() {
        return department;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String userId) {
        this.departmentId = userId;
    }

}
