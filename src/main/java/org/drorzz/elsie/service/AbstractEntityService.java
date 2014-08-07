package org.drorzz.elsie.service;

import org.drorzz.elsie.dao.AbstractDAO;
import org.drorzz.elsie.domain.PersistentObject;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
public  interface AbstractEntityService<T extends PersistentObject, M extends AbstractDAO<T>>{
    public void setEntityDAO(M entityDAO);

    public T create();
    public void save(T obj);
    public void delete(T obj);
    public void deleteById(Integer id);
    public void refresh(T obj);

    public List<T> getAll();
    public List<T> getAllWithOrder(String orderField);

    public T getById(Integer id);
    public List<T> getByField(String fieldName, Object fieldValue);
    public List<T> getByFieldWithOrder(String fieldName, Object fieldValue, String orderField);
}
