package org.drorzz.elsie.service;

import org.drorzz.elsie.dao.AbstractDAO;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@SuppressWarnings("UnusedDeclaration")
@Transactional(readOnly = true)
public  interface AbstractEntityService<E, M extends AbstractDAO<E>>{

    void setEntityDAO(M entityDAO);

    E get(Serializable id);

    @Transactional(readOnly = false)
    E create();

    @Transactional(readOnly = false)
    void save(E obj);

    @Transactional(readOnly = false)
    void delete(E obj);

    @Transactional(readOnly = false)
    void deleteById(Serializable id);

    void refresh(E obj);

    E initialize(E detachedParent, String fieldName);

    Long getCount();

    List<E> getAll();
    List<E> getAll(String orderField, String sortDirection);

    List<E> getByField(String fieldName, Object fieldValue);
    List<E> getByField(String fieldName, Object fieldValue, String orderDirection);
    List<E> getByField(String fieldName, Object fieldValue, String orderField, String orderDirection);

    List<E> getLike(String property, String value);

    List<E> getPage(int skip, int pageSize);
    List<E> getPage(int skip, int pageSize, String sortField, String sortDirection);
}
