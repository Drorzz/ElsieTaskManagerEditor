package org.drorzz.dao;

import org.drorzz.model.Department;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 17.12.13
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentDAOImpl extends AbstractDAOImpl<Department> implements DepartmentDAO{

public DepartmentDAOImpl() {
        super(Department.class);
}

@Override
public Department getByName(String name) {
        return (Department) getCurrentSession().bySimpleNaturalId(genericClass).load(name);
}

}
