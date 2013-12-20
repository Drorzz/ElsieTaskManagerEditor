package org.drorzz.bean.convert;

import org.drorzz.dao.DepartmentDAO;
import org.drorzz.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

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
public class DepartmentConverter extends ObjectIdAbstractConverter<Department> {
    @Autowired
    private DepartmentDAO departmentDAO;

    protected DepartmentConverter() {
        super(Department.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) throws ConverterException {
        return getAsObject(departmentDAO,value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) throws ConverterException {
        return getAsString(value);
    }
}
