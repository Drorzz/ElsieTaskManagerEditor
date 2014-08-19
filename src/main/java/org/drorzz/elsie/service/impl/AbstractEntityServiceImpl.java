package org.drorzz.elsie.service.impl;

import org.drorzz.elsie.dao.AbstractDAO;
import org.drorzz.elsie.service.AbstractEntityService;
import org.drorzz.elsie.utils.PageHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.drorzz.elsie.utils.PropertyUtilsWrapper.getProperty;

/**
 * Created by Drorzz on 08.08.2014.
 */
public abstract class AbstractEntityServiceImpl<E, D extends AbstractDAO<E>> implements AbstractEntityService<E, D>{
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
    @Override
    public void setEntityDAO(D entityDAO) {
        this.entityDAO = entityDAO;
    }

    @Override
    public E get(Serializable id) {
        return entityDAO.get(id);
    }

    @Override
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

    @Override
    public void save(E entity) {
        entityDAO.save(entity);
    }

    @Override
    public void delete(E entity) {
        logger.warn("Delete {} with id: {}", getClassName(), getProperty(entity, "id"));
        entityDAO.delete(entity);
    }

    @Override
    public void deleteById(Serializable id) {
        logger.warn("Delete {} with id: {}", getClassName(), id);
        entityDAO.deleteById(id);
    }

    @Override
    public void refresh(E entity) {
        entityDAO.refresh(entity);
    }

    @Override
    public E initialize(E detachedParent, String fieldName){
        return entityDAO.initialize(detachedParent, fieldName);
    }

    @Override
    public Long getCount(){
        return entityDAO.getCount();
    }

    @Override
    public List<E> getAll() {
        return entityDAO.getAll();
    }

    @Override
    public List<E> getAll(String orderField, String sortDirection) {
        return entityDAO.getAll(orderField, sortDirection);
    }

    @Override
    public List<E> getByField(String fieldName, Object fieldValue) {
        return entityDAO.getByField(fieldName, fieldValue);
    }

    @Override
    public List<E> getByField(String fieldName, Object fieldValue, String orderDirection){
        return entityDAO.getByField(fieldName, fieldValue, orderDirection);
    }

    @Override
    public List<E> getByField(String fieldName, Object fieldValue, String orderField, String orderDirection){
        return entityDAO.getByField(fieldName, fieldValue, orderField, orderDirection);
    }

    @Override
    public List<E> getLike(String property, String value) {
        return entityDAO.getLike(property, value);
    }

    @Override
    public PageHolder getPageHolder() {
        return getPageHolder(PAGE_SIZE);
    }

    @Override
    public PageHolder getPageHolder(int pageSize) {
        return new PageHolder(getCount().intValue(),pageSize);
    }

    @Override
    public List<E> getPage(PageHolder pageHolder) {
        return entityDAO.getPage(pageHolder.getFirstElementOnPage(), pageHolder.getPageSize());
    }

    @Override
    public List<E> getPage(PageHolder pageHolder, String sortField, String sortDirection) {
        return entityDAO.getPage(pageHolder.getFirstElementOnPage(), pageHolder.getPageSize(), sortField, sortDirection);
    }
}
