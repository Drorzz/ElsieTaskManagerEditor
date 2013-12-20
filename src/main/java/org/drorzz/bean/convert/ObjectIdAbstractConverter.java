package org.drorzz.bean.convert;

import org.drorzz.dao.AbstractDAO;
import org.drorzz.model.PersistentObject;

import javax.faces.application.FacesMessage;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 20.12.13
 * Time: 10:11
 */
public abstract class ObjectIdAbstractConverter<T extends PersistentObject> implements Converter {
    private Class<T> entityClass;

    protected ObjectIdAbstractConverter(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    protected  <T> T getAsObject(AbstractDAO objectDAO,String id){
        System.out.println(id);
        if (id == null || !id.matches("^(new|\\d+)$")) {
            return null;
        }
        try {
            return (T) objectDAO.getById(Integer.valueOf(id.trim()));
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
