package org.drorzz.elsie.service.impl;

import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.domain.Department;
import org.drorzz.elsie.domain.User;
import org.drorzz.elsie.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@Service
public class DepartmentServiceImpl extends AbstractEntityServiceImpl<Department,DepartmentDAO> implements DepartmentService {
    private final static Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List<Department> getByName(String name) {
        List<Department> list = entityDAO.getByName(name);
        logger.info("Get {} by name, value: {}. Count: {}", getClassName(), name, list.size());
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersList(Department department) {
//        List<User> list = entityDAO.getUsersList(department);
        List<User> list = department.getUsersList();
        logger.info("({})Get users list for department: {}. Count: {}", getClassName(), department.getId(), list.size());
        return list;
    }
}
