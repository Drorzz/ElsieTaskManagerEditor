/*
 * Copyright (c) 2013. Denis Ivansky. All Rights Reserved. No part of this website may be reproduced without Denis Ivansky's express consent.
 */

package org.drorzz.bean.convert;

import org.drorzz.dao.DepartmentDAO;
import org.drorzz.model.Department;

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
@FacesConverter(value = "departmentConverterTest", forClass = Department.class)
public class DepartmentConverter extends ObjectIdAbstractConverter<Department,DepartmentDAO>{

    public DepartmentConverter() {
        super(Department.class,"departmentDAO");
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) throws ConverterException {
        return getAsObject(facesContext,value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) throws ConverterException {
        return getAsString(value);
    }

}
