package org.drorzz.elsie.web.controller;

import org.drorzz.elsie.domain.Department;
import org.drorzz.elsie.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/departments")
public class DepartmentController extends AbstractEntityController<Department, DepartmentService> {
    private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController() {
        super("departmentList", "department");
    }

    @Override
    protected void addEntityListMappingModelAttributes(Model model) {
    }

    @Override
    protected void addEntityByIdMappingModelAttributes(Model model, Department entity) {
//        System.out.println(Arrays.asList(entity.getUsersList()));
//        System.out.println(Arrays.asList(entityService.getUsersList(entity)));

    }
}
