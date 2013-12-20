package org.drorzz.dao;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 16:09
 */


import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface AbstractDAO<T> {

    public <T> T create() throws IllegalAccessException, InstantiationException;
    public void save(T obj);
    public void delete(T obj);
    public void deleteById(Integer id);

    public  <T> List<T> getAll();

    public <T> T getById(Integer id);
    public <T> List<T> getByField(String fieldName,Object fieldValue);
}
