package org.drorzz.elsie.dao;

import org.drorzz.elsie.domain.PersistentObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 16:09
 */
public interface AbstractDAO<E extends PersistentObject> {
    public void save(E obj);
    public void delete(E obj);
    public void deleteById(Integer id);
    public void refresh(E obj);

    public List<E> getAll();
    public List<E> getAllWithOrder(String orderField);
    public List<E> getAllWithOrder(String orderField,OrderEnum order);

    public E get(Serializable id);
    public List<E> getByField(String fieldName,Object fieldValue);
    public List<E> getByFieldWithOrder(String fieldName,Object fieldValue,String orderField);
}
