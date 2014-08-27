package org.drorzz.elsie.tme.dao;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 16:09
 */
@SuppressWarnings("UnusedDeclaration")
public interface AbstractDAO<E> {
    E get(Serializable id);

    E save(E entity);
    E merge(E entity);

    void delete(E entity);
    void deleteById(Serializable id);

    E initialize(E detachedParent, String fieldName);

    void refresh(E entity);

    Long getCount();
    Long getCount(List<String> aliases, List<Criterion> criterions);

    List<E> getAll();
    List<E> getAll(String orderField, String sortDirection);

    List<E> getByField(String fieldName, Object fieldValue);
    List<E> getByField(String fieldName, Object fieldValue, String orderDirection);
    List<E> getByField(String fieldName, Object fieldValue, String orderField, String orderDirection);

    List<E> getLike(String property, String value);
    List<E> getLike(String property, String value, MatchMode matchMode);

    List<E> getPage(int skip, int pageSize);
    List<E> getPage(int skip, int pageSize, String orderField, String orderDirection);
    List<E> getPage(int skip, int pageSize, String orderField, String orderDirection, List<String> aliases, List<Criterion> criterions);
}
