package org.drorzz.elsie.tme.web.controller;

import org.drorzz.elsie.tme.domain.Department;
import org.drorzz.elsie.tme.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/departments")
public class DepartmentController extends AbstractEntityController<Department, DepartmentService> {
    private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController() {
        super("departmentList", "department");
    }

    @Override
    protected void addEntityListMappingModelAttributes(Model model, List<Department> entityList, int page) {
    }

    @Override
    protected void addEntityByIdMappingModelAttributes(Model model, Department entity) {
//        System.out.println(Arrays.asList(entity.getUsersList()));
//        System.out.println(Arrays.asList(entityService.getUsersList(entity)));

    }
}
