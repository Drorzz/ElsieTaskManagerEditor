package org.drorzz.elsie.controller;

import org.apache.log4j.Logger;
import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/departments")
public class DepartmentController {
    private static final Logger logger = Logger.getLogger(DepartmentController.class);
    private static final String departmentIdEditMask = "[1-9]+|new";

    DepartmentDAO departmentDAO;

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String departmentList(Model model) {
        List<Department> departmentList = departmentDAO.getAll();
        logger.info("Count: "+departmentList.size());
        model.addAttribute("departmentList", departmentList);
        return "departmentList";
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String wrongDepartment() {
        return redirectToList();
    }

    @RequestMapping(value = "/{pathId:"+ departmentIdEditMask +"}", method = RequestMethod.GET)
    public String departmentById(Model model, @PathVariable(value = "pathId") String pathId) {

        Department department;
        if (pathId.toLowerCase().equals("new")){
            department = createDepartment();
        }else{
            try{
                Integer intId = Integer.valueOf(pathId);
                department = departmentDAO.getById(intId);
            }catch(NumberFormatException e){
                return redirectToList();
            }
        }
        if(department == null){
            return redirectToList();
        }
        logger.info("DepartmentById. ID: " + department.getId());

        model.addAttribute("department", department);

        return "department";
    }

    @RequestMapping(value = "/{pathId:"+ departmentIdEditMask +"}", method = RequestMethod.POST)
    public String departmentSave(@ModelAttribute("department") Department department) {
        if(department != null) {
            departmentDAO.save(department);
        }
        return redirectToList();
    }

    private String redirectToList(){
        return "redirect:/departments";
    }

    private Department createDepartment(){
        return departmentDAO.create();
    }
}
