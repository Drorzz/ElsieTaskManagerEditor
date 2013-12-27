package org.drorzz.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drorzz.dao.AbstractDAO;
import org.drorzz.model.PersistentObject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 16:14
 */

public abstract class AbstractDAOImpl<T extends PersistentObject> implements AbstractDAO<T> {
    protected static final Logger LOG = LogManager.getLogger(AbstractDAOImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    protected Class<T> genericClass;

    protected AbstractDAOImpl(Class<T> genericClass) {
        this.genericClass = genericClass;
    }

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T create() {
        try {
            T newEntity = genericClass.newInstance();
            LOG.info(String.format("Create new %s",newEntity.getClass().getSimpleName()));
            return newEntity;
        } catch (IllegalAccessException|InstantiationException e) {
            String errorMessage = String.format("Cannot create new %s",genericClass.getSimpleName());
            LOG.error(errorMessage, e);
            throw new RuntimeException(errorMessage,e);
        }
    }

    @Override
    public void save(T obj) {
        getCurrentSession().saveOrUpdate(obj);
        LOG.info(String.format("Save %s with id=%s", genericClass.getSimpleName(), obj.getId()));
    }

    @Override
    public void delete(T obj) {
        getCurrentSession().delete(obj);
        LOG.warn(String.format("Delete %s with id=%s", genericClass.getSimpleName(), obj.getId()));
    }

    @Override
    public void deleteById(Integer id) {
        delete(getById(id));
    }

    @Override
    public void refresh(T obj) {
        getCurrentSession().refresh(obj);
        if (LOG.isInfoEnabled()) {
            LOG.info(String.format("Save %s with id=%s",genericClass.getSimpleName(),obj.getId()));
        }
    }

    protected Criteria getAllCriteria(){
        return getCurrentSession().createCriteria(genericClass);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<T> getAll() {
        return (List<T>) getAllCriteria().list();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<T> getAllWithOrder(String orderField) {
        return (List<T>) getAllCriteria().addOrder(Order.asc(orderField)).list();
    }

    protected Criteria getByFieldCriteria(String fieldName, Object fieldValue){
        return getCurrentSession().createCriteria(genericClass).add(Restrictions.eq(fieldName, fieldValue));
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<T> getByField(String fieldName, Object fieldValue) {
        return (List<T>) getByFieldCriteria(fieldName,fieldValue).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<T> getByFieldWithOrder(String fieldName, Object fieldValue, String orderField) {
        return (List<T>) getByFieldCriteria(fieldName,fieldValue).addOrder(Order.asc(orderField)).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public T getById(Integer id) {
        return (T) getCurrentSession().byId(genericClass).load(id);
    }
}