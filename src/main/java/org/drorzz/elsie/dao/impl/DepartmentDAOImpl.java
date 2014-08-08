package org.drorzz.elsie.dao.impl;

import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 17.12.13
 * Time: 16:08
 */
@Repository
public class DepartmentDAOImpl extends AbstractDAOImpl<Department> implements DepartmentDAO {
    @Override
    public List<Department> getByName(String name) {
        return this.getByField("name",name);
    }
}
