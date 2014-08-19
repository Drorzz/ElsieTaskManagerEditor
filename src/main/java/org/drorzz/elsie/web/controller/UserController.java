package org.drorzz.elsie.web.controller;

import org.drorzz.elsie.domain.AccessLevel;
import org.drorzz.elsie.domain.Department;
import org.drorzz.elsie.domain.User;
import org.drorzz.elsie.service.DepartmentService;
import org.drorzz.elsie.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController extends AbstractEntityController<User, UserService>  {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private DepartmentService departmentService;

    public UserController() {
        super("userList", "user");
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    private List<Department> departmentsList(){
        List<Department> departmentList = departmentService.getAll();
        logger.info("Department list size: {}.",departmentList.size());
        return departmentList;
    }

    private List<AccessLevel> accessLevelsList(){
        logger.info("Access level list.");
        return Arrays.asList(AccessLevel.values());
    }

    @Override
    protected void addEntityListMappingModelAttributes(Model model, List<User> entityList, int page) {
    }

    @Override
    protected void addEntityByIdMappingModelAttributes(Model model, User entity) {
        model.addAttribute("userList", entityList());
        model.addAttribute("departmentList", departmentsList());
        model.addAttribute("accessLevelList", accessLevelsList());
    }
}
