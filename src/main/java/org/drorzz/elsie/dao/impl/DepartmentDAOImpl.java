package org.drorzz.elsie.dao.impl;

import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.domain.Department;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 17.12.13
 * Time: 16:08
 */
@Repository
public class DepartmentDAOImpl extends AbstractDAOImpl<Department> implements DepartmentDAO {
    public DepartmentDAOImpl() {
            super(Department.class);
    }

    @Override
    public Department getByName(String name) {
            return (Department) getCurrentSession().bySimpleNaturalId(genericClass).load(name);
    }
}
