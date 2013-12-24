package org.drorzz.bean.convert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drorzz.dao.AbstractDAO;
import org.drorzz.model.PersistentObject;

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
    private static Logger LOG = LogManager.getLogger(ObjectIdAbstractConverter.class.getName());

    private Class<T> entityClass;
    private String daoBeanName;
    private K dao;

    protected ObjectIdAbstractConverter(Class<T> entityClass,String daoBeanName){
        this.entityClass = entityClass;
        this.daoBeanName = daoBeanName;
    }

    @SuppressWarnings("unchecked")
    protected T getAsObject(FacesContext facesContext,String id){
        if(dao == null){

            dao = (K)facesContext.getApplication().getELResolver().getValue(
                    facesContext.getELContext(), null, daoBeanName);
        }
        try {
            return dao.getById(Integer.valueOf(id.trim()));
        } catch (Exception e){
            String message = getConvertErrorMessage(id);
            LOG.error(message, e);
            throw new ConverterException(new FacesMessage(message), e);
        }
    }

    @SuppressWarnings("unchecked")
    protected String getAsString(Object value){
        if ((value == null) || !(entityClass.isAssignableFrom(value.getClass())) || ((T) value).getId() == null) {
            return null;
        }
        try {
            return (((T) value).getId()).toString();
        } catch (Exception e){
            String message = getConvertErrorMessage(value);
            LOG.error(message, e);
            throw new ConverterException(new FacesMessage(message), e);
        }
    }

    private String getConvertErrorMessage(Object value){
        return String.format("Cannot convert %s to %s class", value, entityClass.getName());
    }
}
