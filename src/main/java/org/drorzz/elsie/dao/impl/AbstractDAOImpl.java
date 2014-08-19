package org.drorzz.elsie.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.drorzz.elsie.dao.AbstractDAO;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hibernate.criterion.Restrictions.like;
import static org.drorzz.elsie.utils.PropertyUtilsWrapper.getProperty;
import static org.drorzz.elsie.utils.PropertyUtilsWrapper.setProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 16:14
 */

@SuppressWarnings("unchecked")
public abstract class AbstractDAOImpl<E> implements AbstractDAO<E> {
    private final static Logger logger = LoggerFactory.getLogger(AbstractDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected final Class<E> entityClass;

    protected String getClassName(){
        return entityClass.getSimpleName();
    }

    protected AbstractDAOImpl() {
        this.entityClass = (Class<E>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected final Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    protected Criteria criteria(){
        return currentSession().createCriteria(entityClass);
    }

    protected Criteria fieldCriteria(String fieldName, Object fieldValue){
        return currentSession().createCriteria(entityClass).add(Restrictions.eq(fieldName, fieldValue));
    }

    protected Order getOrder(String orderField, String orderDirection){
        return "asc".equals(orderDirection) ? Order.asc(orderField) : Order.desc(orderField);
    }

    protected List<E> list(Criteria criteria) {
        return list(criteria, true);
    }

    protected List<E> list(Criteria criteria, boolean cache) {
        criteria.setCacheable(cache);
        return new ArrayList<>(new LinkedHashSet<E>(criteria.list())); // privent duplications
    }

    @Override
    public E get(Serializable id) {
        return (E) currentSession().get(entityClass, id);
    }

    @Override
    public E save(E entity) {
//        try {
//            currentSession().saveOrUpdate(obj);
//        }catch (NonUniqueObjectException ex){
//            logger.warn("NonUniqueObjectException: {} with id = {}.", getClassName(), obj.getId());
//            currentSession().merge(obj);
//        }
        ClassMetadata metadata = sessionFactory.getClassMetadata(entityClass);
        logger.info("Save {} with id: {}", getClassName(), getProperty(entity, metadata.getIdentifierPropertyName()));
        Object id = getProperty(entity, metadata.getIdentifierPropertyName());
        if (id != null && id.equals(0)) {
            setProperty(entity, metadata.getIdentifierPropertyName(), null);
        }

        currentSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public E merge(E entity) {
        logger.info("Save {} with id: {}", getClassName(), getProperty(entity,"id"));
        return (E) currentSession().merge(entity);
    }

    @Override
    public E initialize(E detachedParent, String fieldName) {
        // ...open a hibernate session...
        // reattaches parent to session
        E reattachedParent = (E) currentSession().merge(detachedParent);

        // get the field from the entity and initialize it
        try {
            Field fieldToInitialize = detachedParent.getClass().getDeclaredField(fieldName);
            fieldToInitialize.setAccessible(true);
            Object objectToInitialize = fieldToInitialize.get(reattachedParent);
            Hibernate.initialize(objectToInitialize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return reattachedParent;
    }

    @Override
    public void delete(E entity) {
        logger.warn("Delete {} with id: {}", getClassName(), getProperty(entity, "id"));
        currentSession().delete(entity);
    }

    @Override
    public void deleteById(Serializable id) {
        delete(get(id));
    }

    @Override
    public void refresh(E entity) {
        currentSession().refresh(entity);
    }

    public Long getCount() {
        return (Long) currentSession().createQuery("select count (*) from "+entityClass.getName())
                .uniqueResult();
    }

    @Override
    public Long getCount(List<String> aliases, List<Criterion> criterions) {
        Criteria criteria = criteria();

        for (String alias : aliases) {
            criteria.createAlias(alias, alias);
        }
        for (Criterion item : criterions) {
            criteria.add(item);
        }
        criteria.setProjection(Projections.rowCount());

        return (Long) criteria.uniqueResult();
    }

    @Override
    public List<E> getAll() {
        return (List<E>) criteria().list();
    }

    @Override
    public List<E> getAll(String orderField, String orderDirection) {
         return (List<E>) criteria().addOrder(getOrder(orderField, orderDirection)).list();
    }

    @Override
    public List<E> getByField(String fieldName, Object fieldValue) {
        return (List<E>) fieldCriteria(fieldName, fieldValue).list();
    }

    @Override
    public List<E> getByField(String fieldName, Object fieldValue, String orderDirection) {
        return (List<E>) fieldCriteria(fieldName, fieldValue).addOrder(getOrder(fieldName, orderDirection)).list();
    }

    @Override
    public List<E> getByField(String fieldName, Object fieldValue, String orderField, String orderDirection) {
        return (List<E>) fieldCriteria(fieldName, fieldValue).addOrder(getOrder(orderField, orderDirection)).list();
    }

    @Override
    public List<E> getLike(String property, String value) {
        return getLike(property, value, MatchMode.ANYWHERE);
    }

    @Override
    public List<E> getLike(String property, String value, MatchMode matchMode) {
        return criteria()
                .add(like(property, value, matchMode))
                .list();
    }

    protected Criteria getPageCriteria(int skip, int pageSize) {
        return criteria().setFirstResult(skip).setMaxResults(pageSize);
    }

    protected Criteria getPageCriteria(int skip, int pageSize, String orderField, String orderDirection) {
        Criteria criteria = getPageCriteria(skip,pageSize);

        if (orderField != null && !orderField.isEmpty()) {
            criteria.addOrder(getOrder(orderField, orderDirection));
        }

        return criteria;
    }

    @Override
    public List<E> getPage(int skip, int pageSize) {
        return list(getPageCriteria(skip,pageSize));
    }

    @Override
    public List<E> getPage(int skip, int pageSize, String orderField, String orderDirection) {
         return list(getPageCriteria(skip,pageSize,orderField,orderDirection));
    }

    @Override
    public List<E> getPage(int skip, int pageSize, String orderField, String orderDirection,
                           List<String> aliases, List<Criterion> criterions) {
        Criteria criteria = getPageCriteria(skip,pageSize,orderField,orderDirection);
        for (String alias : aliases) {
            criteria.createAlias(alias, alias);
        }
        for (Criterion item : criterions) {
            criteria.add(item);
        }
        return list(criteria);
    }
}
