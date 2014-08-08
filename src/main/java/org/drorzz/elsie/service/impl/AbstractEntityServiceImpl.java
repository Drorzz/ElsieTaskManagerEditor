package org.drorzz.elsie.service.impl;

import org.drorzz.elsie.dao.AbstractDAO;
import org.drorzz.elsie.domain.PersistentObject;
import org.drorzz.elsie.service.AbstractEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
public class AbstractEntityServiceImpl<T extends PersistentObject, M extends AbstractDAO<T>> implements AbstractEntityService<T, M>{
    private final static Logger logger = LoggerFactory.getLogger(AbstractEntityServiceImpl.class);

    protected M entityDAO;

    protected Class<T> entityClass;

    protected String getClassName(){
        return entityClass.getSimpleName();
    }

    @SuppressWarnings("unchecked")
    protected AbstractEntityServiceImpl() {
        this.entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    public void setEntityDAO(M entityDAO) {
        this.entityDAO = entityDAO;
    }

    @Transactional
    public T create() {
        logger.info("Create new {}", getClassName());
        return entityDAO.create();
    }

    @Transactional
    public void save(T obj) {
        logger.info("Save {} with id: {}", getClassName(), obj.getId());
        entityDAO.save(obj);
    }

    @Transactional
    public void delete(T obj) {
        logger.warn("Delete {} with id: {}", getClassName(), obj.getId());
        entityDAO.delete(obj);
    }

    @Transactional
    public void deleteById(Integer id) {
        entityDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    public void refresh(T obj) {
        logger.info("Refresh {} with id: {}", getClassName(),obj.getId());
        entityDAO.refresh(obj);
    }

    @Transactional(readOnly = true)
    public List<T> getAll() {
        List<T> list = entityDAO.getAll();
        logger.info("Get all {}. Count: {}", getClassName(),list.size());
        return list;
    }

    @Transactional(readOnly = true)
    public List<T> getAllWithOrder(String orderField) {
        List<T> list = entityDAO.getAllWithOrder(orderField);
        logger.info("Get all {}. Order: {}. Count: {}", getClassName(), orderField, list.size());
        return list;
    }

    @Transactional(readOnly = true)
    public T getById(Integer id) {
        logger.info("Get {} by id: {}", getClassName(),id);
        return entityDAO.getById(id);
    }

    @Transactional(readOnly = true)
    public List<T> getByField(String fieldName, Object fieldValue) {
        List<T> list = entityDAO.getByField(fieldName,fieldValue);
        logger.info("Get {} by field {} value: {}. Count: {}", getClassName(), fieldName, fieldValue, list.size());
        return list;
    }

    @Transactional(readOnly = true)
    public List<T> getByFieldWithOrder(String fieldName, Object fieldValue, String orderField) {
        List<T> list = entityDAO.getByFieldWithOrder(fieldName,fieldValue,orderField);
        logger.info("Get {} by field {} value: {}. Order: {}. Count: {}", getClassName(), fieldName, fieldValue, orderField, list.size());
        return list;
    }
}
