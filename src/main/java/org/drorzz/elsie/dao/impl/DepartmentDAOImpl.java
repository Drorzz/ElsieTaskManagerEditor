package org.drorzz.elsie.dao.impl;

import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.domain.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(DepartmentDAOImpl.class);
    @Override
    public List<Department> getByName(String name) {
        List<Department> list = this.getByField("name",name);
        logger.info("Get {} by name, value: {}. Count: {}", getClassName(), name, list.size());
        return list;
    }
}
