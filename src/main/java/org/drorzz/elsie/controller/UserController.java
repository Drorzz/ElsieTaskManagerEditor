package org.drorzz.elsie.controller;

import java.util.List;

import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.dao.UserDAO;
import org.drorzz.elsie.domain.User;
import org.drorzz.elsie.domain.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    private static final String userIdEditMask = "[1-9]+|new";

    UserDAO userDAO;
    DepartmentDAO departmentDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String userList(Model model) {
        List<User> userList = userDAO.getAll();
        logger.info("Count:" + userList.size());
        model.addAttribute("userList", userList);
        return "userList";
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String wrongUser() {
        return redirectToList();
    }

    @RequestMapping(value = "/{pathId:"+ userIdEditMask +"}", method = RequestMethod.GET)
    public String userById(Model model, @PathVariable(value = "pathId") String pathId) {
        User user;
        if (pathId.toLowerCase().equals("new")){
            user = createUser();
        }else{
            try{
                Integer intId = Integer.valueOf(pathId);
                user = userDAO.getById(intId);
            }catch(NumberFormatException e){
                return redirectToList();
            }
        }
        if(user == null){
            return redirectToList();
        }
        logger.info("UserById. ID: " + user.getId());

        model.addAttribute("user", user);
        model.addAttribute("departmentList", departmentDAO.getAll());
        model.addAttribute("userList", userDAO.getAll());
        model.addAttribute("accessLevelList", AccessLevel.values());

        return "user";
    }

    @RequestMapping(value = "/{pathId:"+ userIdEditMask +"}", method = RequestMethod.POST)
    public String userSave(@ModelAttribute("user") User user) {
        if(user != null) {
            userDAO.save(user);
        }
        return redirectToList();
    }

    private String redirectToList(){
        return "redirect:/users";
    }

    private User createUser(){
        return userDAO.create();
    }
}
