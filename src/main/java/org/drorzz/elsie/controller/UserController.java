package org.drorzz.elsie.controller;

import java.util.List;
import java.util.Locale;

import org.drorzz.elsie.dao.DepartmentDAO;
import org.drorzz.elsie.dao.UserDAO;
import org.drorzz.elsie.domain.User;
import org.drorzz.elsie.domain.AccessLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class.getName());

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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String user(Locale locale, Model model) {
        logger.info("UserList. The client local is {}.", locale);

        List<User> userList = userDAO.getAll();

        logger.info("Count {}.", userList.size());

        model.addAttribute("userList", userList);

        return "userList";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String userById(Locale locale, Model model, @PathVariable(value = "id") String id ) {
        logger.info("UserById. The client local is {}.", locale);

        User user;
        if (id.toLowerCase().equals("new")){
            user = createUser();
        }else{
            try{
                Integer intId = Integer.valueOf(id);
                user = userDAO.getById(intId);
                if(user == null){
                    user = createUser();
                }
            }catch(NumberFormatException e){
                user = createUser();
            }
        }
        logger.info("UserById. ID: {}.", user.getId());

        model.addAttribute("user", user);
        model.addAttribute("departmentList", departmentDAO.getAll());
        model.addAttribute("userList", userDAO.getAll());
        model.addAttribute("accessLevelList", AccessLevel.values());

        return "user";
    }

    private User createUser(){
        return userDAO.create();
    }
}
