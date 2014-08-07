package org.drorzz.elsie.service.impl;

import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.domain.Department;
import org.drorzz.elsie.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@Service
public class DepartmentServiceImpl extends AbstractEntityServiceImpl<Department,DepartmentDAO> implements DepartmentService {
    @Override
    @Transactional(readOnly = true)
    public List<Department> getByName(String name) {
        return entityDAO.getByName(name);
    }
}
