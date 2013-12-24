package org.drorzz.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 16:14
 */

public abstract class  AbstractDAOImpl<T> implements AbstractDAO<T> {
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
    public T create() throws IllegalAccessException, InstantiationException {
        return genericClass.newInstance();
    }

    @Override
    public void save(T obj) {
        getCurrentSession().saveOrUpdate(obj);
    }

    @Override
    public void delete(T obj) {
        getCurrentSession().delete(obj);
    }

    @Override
    public void deleteById(Integer id) {
        delete(getById(id));
    }

    @Override
    public void refresh(T obj) {
        getCurrentSession().refresh(obj);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return (List<T>) getCurrentSession().createCriteria(genericClass).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<T> getByField(String fieldName, Object fieldValue) {
        return (List<T>) getCurrentSession().createCriteria(genericClass).add(Restrictions.eq(fieldName, fieldValue)).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public T getById(Integer id) {
        return (T) getCurrentSession().byId(genericClass).load(id);
    }
}
