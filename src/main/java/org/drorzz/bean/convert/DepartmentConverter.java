package org.drorzz.bean.convert;

import org.drorzz.dao.DepartmentDAO;
import org.drorzz.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.IntegerConverter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

/**
 * Created with IntelliJ IDEA.
 * User: Drorzz
 * Date: 19.12.13
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope("request")
@FacesConverter(value = "departmentConverter", forClass = org.drorzz.model.Department.class)
public class DepartmentConverter extends IntegerConverter {
    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) throws ConverterException {
        if (s.trim() == ""){
            return null;
        }
        try {
            return (Department)departmentDAO.getById(Integer.valueOf(s.trim()));
        } catch (Exception e){
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Classification - %s", s, e)), e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) throws ConverterException {
        if (o == null){
            return "";
        }
        try {
            return (((Department) o).getId()).toString();
        } catch (Exception e){
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Classification - %s", o, e)), e);
        }
    }
}
