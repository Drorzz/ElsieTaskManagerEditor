package org.drorzz.elsie.service;

import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.domain.Department;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
public interface DepartmentService extends AbstractEntityService<Department,DepartmentDAO> {
    public List<Department> getByName(String name);
}
