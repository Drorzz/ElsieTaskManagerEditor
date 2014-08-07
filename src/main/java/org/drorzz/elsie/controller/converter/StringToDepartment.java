package org.drorzz.elsie.controller.converter;

import org.apache.log4j.Logger;
import org.drorzz.elsie.domain.Department;
import org.drorzz.elsie.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Drorzz on 07.08.2014.
 */
public class StringToDepartment implements Converter<String, Department> {
    private static final Logger logger = Logger.getLogger(StringToDepartment.class);

    @Autowired
    DepartmentService departmentService;

    @Override
    public Department convert(String source) {
        logger.info("Converting: ".concat(source));
        try {
            return departmentService.getById(Integer.parseInt(source));
        } catch(NumberFormatException nfe){
            throw new RuntimeException(nfe.getMessage());
        }
    }
}
