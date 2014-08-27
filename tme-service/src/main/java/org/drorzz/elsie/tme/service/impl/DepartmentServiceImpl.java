package org.drorzz.elsie.tme.service.impl;

import org.drorzz.elsie.tme.dao.DepartmentDAO;
import org.drorzz.elsie.tme.domain.Department;
import org.drorzz.elsie.tme.domain.User;
import org.drorzz.elsie.tme.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@Service
public class DepartmentServiceImpl extends AbstractEntityServiceImpl<Department,DepartmentDAO> implements DepartmentService {

    @Override
    public List<Department> getByName(String name) {
        return entityDAO.getByName(name);
    }

    @Override
    public List<User> getUsersList(Department department) {
        return department.getUsersList();
    }
}
