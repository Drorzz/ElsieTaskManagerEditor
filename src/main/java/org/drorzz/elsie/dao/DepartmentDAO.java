package org.drorzz.elsie.dao;

import org.drorzz.elsie.domain.Department;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 17.12.13
 * Time: 16:01
 */
public interface DepartmentDAO extends AbstractDAO<Department> {
    List<Department> getByName(String name);
}
