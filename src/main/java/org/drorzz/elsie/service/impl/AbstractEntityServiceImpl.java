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
public abstract class AbstractEntityServiceImpl<E extends PersistentObject, D extends AbstractDAO<E>> implements AbstractEntityService<E, D>{
    private final static Logger logger = LoggerFactory.getLogger(AbstractEntityServiceImpl.class);

    protected D entityDAO;

    protected Class<E> entityClass;

    protected String getClassName(){
        return entityClass.getSimpleName();
    }

    @SuppressWarnings("unchecked")
    protected AbstractEntityServiceImpl() {
        this.entityClass = (Class<E>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setEntityDAO(D entityDAO) {
        this.entityDAO = entityDAO;
    }

    public E create() {
        try {
            E newEntity = entityClass.newInstance();
            logger.info("Create new {}", getClassName());
            return newEntity;
        } catch (IllegalAccessException|InstantiationException e) {
            String errorMessage = String.format("Cannot create new %s", getClassName());
            logger.error(errorMessage, e);
            throw new RuntimeException(errorMessage,e);
        }
    }

    public void save(E obj) {
        logger.info("Save {} with id: {}", getClassName(), obj.getId());
        entityDAO.save(obj);
    }

    public void delete(E obj) {
        logger.warn("Delete {} with id: {}", getClassName(), obj.getId());
        entityDAO.delete(obj);
    }

    public void deleteById(Integer id) {
        entityDAO.deleteById(id);
    }

    public void refresh(E obj) {
        logger.info("Refresh {} with id: {}", getClassName(),obj.getId());
        entityDAO.refresh(obj);
    }

    public List<E> getAll() {
        List<E> list = entityDAO.getAll();
        logger.info("Get all {}. Count: {}", getClassName(),list.size());
        return list;
    }

    public List<E> getAllWithOrder(String orderField) {
        List<E> list = entityDAO.getAllWithOrder(orderField);
        logger.info("Get all {}. Order: {}. Count: {}", getClassName(), orderField, list.size());
        return list;
    }

    @Override
    public List<E> getAllWithOrderDesc(String orderField) {
        List<E> list = entityDAO.getAllWithOrder(orderField,OrderEnum.DESC);
        logger.info("Get all {}. Order({}): {}. Count: {}", getClassName(), OrderEnum.DESC, orderField, list.size());
        return list;
    }

    public E getById(Integer id) {
        logger.info("Get {} by id: {}", getClassName(),id);
        return entityDAO.get(id);
    }

    public List<E> getByField(String fieldName, Object fieldValue) {
        List<E> list = entityDAO.getByField(fieldName, fieldValue);
        logger.info("Get {} by field {} value: {}. Count: {}", getClassName(), fieldName, fieldValue, list.size());
        return list;
    }

    public List<E> getByFieldWithOrder(String fieldName, Object fieldValue, String orderField) {
        List<E> list = entityDAO.getByFieldWithOrder(fieldName,fieldValue,orderField);
        logger.info("Get {} by field {} value: {}. Order: {}. Count: {}", getClassName(), fieldName, fieldValue, orderField, list.size());
        return list;
    }
}
