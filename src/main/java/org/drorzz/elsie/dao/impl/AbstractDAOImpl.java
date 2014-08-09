package org.drorzz.elsie.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.drorzz.elsie.dao.AbstractDAO;
import org.drorzz.elsie.dao.OrderEnum;
import org.drorzz.elsie.domain.PersistentObject;
import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
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

public abstract class AbstractDAOImpl<E extends PersistentObject> implements AbstractDAO<E> {
    private final static Logger logger = LoggerFactory.getLogger(AbstractDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected final Class<E> entityClass;

    protected String getClassName(){
        return entityClass.getSimpleName();
    }

    @SuppressWarnings("unchecked")
    protected AbstractDAOImpl() {
        this.entityClass = (Class<E>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected final Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(E obj) {
        logger.info("Save {} with id: {}", getClassName(), obj.getId());
//        logger.info("Transaction: {}",currentSession().getTransaction().isActive());
        try {
            currentSession().saveOrUpdate(obj);
        }catch (NonUniqueObjectException ex){
            logger.warn("NonUniqueObjectException: {} with id = {}.", getClassName(), obj.getId());
//            obj = this.get(obj.getId());
            currentSession().merge(obj);
        }
    }

    @Override
    public void delete(E obj) {
        logger.warn("Delete {} with id: {}", getClassName(), obj.getId());
        currentSession().delete(obj);
    }

    @Override
    public void deleteById(Integer id) {
        delete(get(id));
    }

    @Override
    public void refresh(E obj) {
        logger.info("Refresh {} with id: {}", getClassName(),obj.getId());
        currentSession().refresh(obj);
    }

    protected Criteria getAllCriteria(){
        return currentSession().createCriteria(entityClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getAll() {
        List<E> list = (List<E>) getAllCriteria().list();
        logger.info("Get all {}. Count: {}", getClassName(),list.size());
        return list;
    }

    @Override
    public List<E> getAllWithOrder(String orderField) {
        return getAllWithOrder(orderField,OrderEnum.ASC);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getAllWithOrder(String orderField, OrderEnum order) {
        List<E> list = (List<E>) getAllCriteria().addOrder(
                order == OrderEnum.ASC ? Order.asc(orderField) : Order.desc(orderField)).list();
        logger.info("Get all {}. Order({}): {}. Count: {}", getClassName(), order,orderField, list.size());
        return list;
    }

    protected Criteria getByFieldCriteria(String fieldName, Object fieldValue){
        return currentSession().createCriteria(entityClass).add(Restrictions.eq(fieldName, fieldValue));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getByField(String fieldName, Object fieldValue) {
        List<E> list = (List<E>) getByFieldCriteria(fieldName, fieldValue).list();
        logger.info("Get {} by field {} value: {}. Count: {}", getClassName(), fieldName, fieldValue, list.size());
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getByFieldWithOrder(String fieldName, Object fieldValue, String orderField) {
        List<E> list = (List<E>) getByFieldCriteria(fieldName,fieldValue).addOrder(Order.asc(orderField)).list();
        logger.info("Get {} by field {} value: {}. Order: {}. Count: {}", getClassName(), fieldName, fieldValue, orderField, list.size());
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(Serializable id) {
        logger.info("Get {} by id: {}", getClassName(),id);
        return (E) currentSession().get(entityClass, id);
    }
}
