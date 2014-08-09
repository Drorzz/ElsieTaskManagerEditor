package org.drorzz.elsie.service.impl;

import org.drorzz.elsie.dao.AbstractDAO;
import org.drorzz.elsie.dao.OrderEnum;
import org.drorzz.elsie.domain.PersistentObject;
import org.drorzz.elsie.service.AbstractEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
public abstract class AbstractEntityServiceImpl<T extends PersistentObject, M extends AbstractDAO<T>> implements AbstractEntityService<T, M>{
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

    public T create() {
        try {
            T newEntity = entityClass.newInstance();
            logger.info("Create new {}", getClassName());
            return newEntity;
        } catch (IllegalAccessException|InstantiationException e) {
            String errorMessage = String.format("Cannot create new %s", getClassName());
            logger.error(errorMessage, e);
            throw new RuntimeException(errorMessage,e);
        }
    }

    public void save(T obj) {
        logger.info("Save {} with id: {}", getClassName(), obj.getId());
        entityDAO.save(obj);
    }

    public void delete(T obj) {
        logger.warn("Delete {} with id: {}", getClassName(), obj.getId());
        entityDAO.delete(obj);
    }

    public void deleteById(Integer id) {
        entityDAO.deleteById(id);
    }

    public void refresh(T obj) {
        logger.info("Refresh {} with id: {}", getClassName(),obj.getId());
        entityDAO.refresh(obj);
    }

    public List<T> getAll() {
        List<T> list = entityDAO.getAll();
        logger.info("Get all {}. Count: {}", getClassName(),list.size());
        return list;
    }

    public List<T> getAllWithOrder(String orderField) {
        List<T> list = entityDAO.getAllWithOrder(orderField);
        logger.info("Get all {}. Order: {}. Count: {}", getClassName(), orderField, list.size());
        return list;
    }

    @Override
    public List<T> getAllWithOrderDesc(String orderField) {
        List<T> list = entityDAO.getAllWithOrder(orderField,OrderEnum.DESC);
        logger.info("Get all {}. Order({}): {}. Count: {}", getClassName(), OrderEnum.DESC, orderField, list.size());
        return list;
    }

    public T getById(Integer id) {
        logger.info("Get {} by id: {}", getClassName(),id);
        return entityDAO.get(id);
    }

    public List<T> getByField(String fieldName, Object fieldValue) {
        List<T> list = entityDAO.getByField(fieldName, fieldValue);
        logger.info("Get {} by field {} value: {}. Count: {}", getClassName(), fieldName, fieldValue, list.size());
        return list;
    }

    public List<T> getByFieldWithOrder(String fieldName, Object fieldValue, String orderField) {
        List<T> list = entityDAO.getByFieldWithOrder(fieldName,fieldValue,orderField);
        logger.info("Get {} by field {} value: {}. Order: {}. Count: {}", getClassName(), fieldName, fieldValue, orderField, list.size());
        return list;
    }
}
