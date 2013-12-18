package org.drorzz.dao;

import org.drorzz.model.Department;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 17.12.13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
public interface DepartmentDAO extends AbstractDAO<Department> {
    public Department getByName(String name);
}
