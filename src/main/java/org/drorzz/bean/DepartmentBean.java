package org.drorzz.bean;

import org.drorzz.dao.DepartmentDAO;
import org.drorzz.model.Department;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 20.12.13
 * Time: 0:39
 */
public class DepartmentBean extends AbstractEditorBean<Department,DepartmentDAO>{
    public void init(){
        super.init("departments");
    }

    public void saveDepartment(){
       super.save();
    }
}
