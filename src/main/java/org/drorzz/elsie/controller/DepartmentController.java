package org.drorzz.elsie.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/departments")
public class DepartmentController {
    private static final Logger logger = LogManager.getLogger(DepartmentController.class.getName());

    DepartmentDAO departmentDAO;

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String department(Locale locale, Model model) {
        logger.info("DepartmentList. The client local is {}.", locale);
        List<Department> departmentList = departmentDAO.getAll();
        logger.info("Count {}.", departmentList.size());
        model.addAttribute("departmentList", departmentList);
        return "departmentList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String departmentById(Locale locale, Model model, @PathVariable(value = "id") String id ) {
        logger.info("DepartmentById. The client local is {}.", locale);

        Department department;
        if (id.toLowerCase().equals("new")){
            department = createDepartment();
        }else{
            try{
                Integer intId = Integer.valueOf(id);
                department = departmentDAO.getById(intId);
            }catch(NumberFormatException e){
                return redirectToList();
            }
        }
        if(department == null){
            return redirectToList();
        }
        logger.info("DepartmentById. ID: {}.", department.getId());

        model.addAttribute("department", department);
        model.addAttribute("departmentList", departmentDAO.getAll());

        return "department";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String departmentSave(Locale locale, Model model,
                                 @PathVariable(value = "id") String id,
                                 @ModelAttribute("department") Department department) {
        logger.info("DepartmentSave. ID: {}.",  department.getId());
        logger.info("DepartmentSave. Name: {}.",  department.getName());

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
