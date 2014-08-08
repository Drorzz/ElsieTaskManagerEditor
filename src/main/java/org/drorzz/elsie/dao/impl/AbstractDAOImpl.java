package org.drorzz.elsie.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.drorzz.elsie.dao.AbstractDAO;
import org.drorzz.elsie.domain.PersistentObject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 16:14
 */

public abstract class AbstractDAOImpl<T extends PersistentObject> implements AbstractDAO<T> {
    private final static Logger logger = LoggerFactory.getLogger(AbstractDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected final Class<T> genericClass;
    
    protected String getClassName(){
        return genericClass.getSimpleName();
    }

    @SuppressWarnings("unchecked")
    protected AbstractDAOImpl() {
        this.genericClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T create() {
        try {
            T newEntity = genericClass.newInstance();
            logger.info("Create new {}", getClassName());
            return newEntity;
        } catch (IllegalAccessException|InstantiationException e) {
            String errorMessage = String.format("Cannot create new %s", getClassName());
            logger.error(errorMessage, e);
            throw new RuntimeException(errorMessage,e);
        }
    }

    @Override
    public void save(T obj) {
        logger.info("Save {} with id: {}", getClassName(), obj.getId());
        getCurrentSession().saveOrUpdate(obj);
    }

    @Override
    public void delete(T obj) {
        logger.warn("Delete {} with id: {}", getClassName(), obj.getId());
        getCurrentSession().delete(obj);
    }

    @Override
    public void deleteById(Integer id) {
        delete(getById(id));
    }

    @Override
    public void refresh(T obj) {
        logger.info("Refresh {} with id: {}", getClassName(),obj.getId());
        getCurrentSession().refresh(obj);
    }

    protected Criteria getAllCriteria(){
        return getCurrentSession().createCriteria(genericClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        List<T> list = (List<T>) getAllCriteria().list();
        logger.info("Get all {}. Count: {}", getClassName(),list.size());
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAllWithOrder(String orderField) {
        List<T> list = (List<T>) getAllCriteria().addOrder(Order.asc(orderField)).list();
        logger.info("Get all {}. Order: {}. Count: {}", getClassName(), orderField, list.size());
        return list;
    }

    protected Criteria getByFieldCriteria(String fieldName, Object fieldValue){
        return getCurrentSession().createCriteria(genericClass).add(Restrictions.eq(fieldName, fieldValue));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getByField(String fieldName, Object fieldValue) {
        List<T> list = (List<T>) getByFieldCriteria(fieldName, fieldValue).list();
        logger.info("Get {} by field {} value: {}. Count: {}", getClassName(), fieldName, fieldValue, list.size());
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getByFieldWithOrder(String fieldName, Object fieldValue, String orderField) {
        List<T> list = (List<T>) getByFieldCriteria(fieldName,fieldValue).addOrder(Order.asc(orderField)).list();
        logger.info("Get {} by field {} value: {}. Order: {}. Count: {}", getClassName(), fieldName, fieldValue, orderField, list.size());
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(Integer id) {
        logger.info("Get {} by id: {}", getClassName(),id);
        return (T) getCurrentSession().byId(genericClass).load(id);
    }
}
