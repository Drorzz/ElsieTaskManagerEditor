package org.drorzz.elsie.service;

import org.drorzz.elsie.dao.AbstractDAO;
import org.drorzz.elsie.domain.PersistentObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@Transactional(readOnly = true)
public  interface AbstractEntityService<T extends PersistentObject, M extends AbstractDAO<T>>{
    public void setEntityDAO(M entityDAO);

    @Transactional(readOnly = false)
    public T create();
    @Transactional(readOnly = false)
    public void save(T obj);
    @Transactional(readOnly = false)
    public void delete(T obj);
    @Transactional(readOnly = false)
    public void deleteById(Integer id);
    public void refresh(T obj);

    public List<T> getAll();
    public List<T> getAllWithOrder(String orderField);
    public List<T> getAllWithOrderDesc(String orderField);

    public T getById(Integer id);
    public List<T> getByField(String fieldName, Object fieldValue);
    public List<T> getByFieldWithOrder(String fieldName, Object fieldValue, String orderField);
}
