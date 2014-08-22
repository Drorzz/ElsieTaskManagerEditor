package org.drorzz.elsie.service;

import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.domain.Department;
import org.drorzz.elsie.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@SuppressWarnings("UnusedDeclaration")
@Transactional(readOnly = true)
public interface DepartmentService extends AbstractEntityService<Department,DepartmentDAO> {
    List<Department> getByName(String name);
    List<User> getUsersList(Department department);
}
