package org.drorzz.elsie.service.impl;

import org.drorzz.elsie.dao.AbstractDAO;
import org.drorzz.elsie.domain.PersistentObject;
import org.drorzz.elsie.service.AbstractEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
public class AbstractEntityServiceImpl<T extends PersistentObject, M extends AbstractDAO<T>> implements AbstractEntityService<T, M>{
    protected M entityDAO;

    @Autowired
    public void setEntityDAO(M entityDAO) {
        this.entityDAO = entityDAO;
    }

    @Transactional
    public T create() {
        return entityDAO.create();
    }

    @Transactional
    public void save(T obj) {
        entityDAO.save(obj);
    }

    @Transactional
    public void delete(T obj) {
        entityDAO.delete(obj);
    }

    @Transactional
    public void deleteById(Integer id) {
        entityDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    public void refresh(T obj) {
        entityDAO.refresh(obj);
    }

    @Transactional(readOnly = true)
    public List<T> getAll() {
        return entityDAO.getAll();
    }

    @Transactional(readOnly = true)
    public List<T> getAllWithOrder(String orderField) {
        return entityDAO.getAllWithOrder(orderField);
    }

    @Transactional(readOnly = true)
    public T getById(Integer id) {
        return entityDAO.getById(id);
    }

    @Transactional(readOnly = true)
    public List<T> getByField(String fieldName, Object fieldValue) {
        return entityDAO.getByField(fieldName,fieldValue);
    }

    @Transactional(readOnly = true)
    public List<T> getByFieldWithOrder(String fieldName, Object fieldValue, String orderField) {
        return entityDAO.getByFieldWithOrder(fieldName,fieldValue,orderField);
    }
}
