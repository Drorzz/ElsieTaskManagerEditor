package org.drorzz.dao;

import org.drorzz.model.Department;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 17.12.13
 * Time: 16:01
 */
public interface DepartmentDAO extends AbstractDAO<Department> {
    public Department getByName(String name);
}
