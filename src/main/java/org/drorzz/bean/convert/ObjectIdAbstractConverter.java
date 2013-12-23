package org.drorzz.bean.convert;

import org.drorzz.dao.AbstractDAO;
import org.drorzz.dao.DepartmentDAO;
import org.drorzz.model.PersistentObject;

import javax.el.ELResolver;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 20.12.13
 * Time: 10:11
 */
public abstract class ObjectIdAbstractConverter<T extends PersistentObject,K extends AbstractDAO<T>> implements Converter {
    private Class<T> entityClass;
    private String daoBeanName;
    private K dao;

    protected ObjectIdAbstractConverter(Class<T> entityClass,String daoBeanName){
        this.entityClass = entityClass;
        this.daoBeanName = daoBeanName;
    }

    protected T getAsObject(FacesContext facesContext,String id){
        if(dao == null){
            dao = (K)facesContext.getApplication().getELResolver().getValue(
                    facesContext.getELContext(), null, daoBeanName);
        }
        try {
            return (T) dao.getById(Integer.valueOf(id.trim()));
        } catch (Exception e){
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Classification - %s", id, e)), e);
        }
    }

    protected String getAsString(Object value){
        if ((value == null) || !(entityClass.isAssignableFrom(value.getClass())) || ((T) value).getId() == null) {
            return null;
        }
        try {
            return (((T) value).getId()).toString();
        } catch (Exception e){
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Classification - %s", value, e)), e);
        }
    }
}
