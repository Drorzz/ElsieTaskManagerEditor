package org.drorzz.elsie.controller;

import java.util.List;

import org.drorzz.elsie.domain.User;
import org.drorzz.elsie.domain.AccessLevel;
import org.drorzz.elsie.service.DepartmentService;
import org.drorzz.elsie.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final static String userIdEditMask = "[1-9]+|new";

    private UserService userService;
    private DepartmentService departmentService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String userList(Model model) {
        List<User> userList = userService.getAll();
        logger.info("User list size: {}.",userList.size());
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
                user = userService.getById(intId);
            }catch(NumberFormatException e){
                return redirectToList();
            }
        }
        if(user == null){
            return redirectToList();
        }
        logger.info("User id: {}.", user.getId());

        model.addAttribute("user", user);
        model.addAttribute("departmentList", departmentService.getAll());
        model.addAttribute("userList", userService.getAll());
        model.addAttribute("accessLevelList", AccessLevel.values());

        return "user";
    }

    @RequestMapping(value = "/{pathId:"+ userIdEditMask +"}", method = RequestMethod.POST)
    public String userSave(@ModelAttribute("user") User user) {
        logger.info("Save department. Id: {}", user.getId());
        userService.save(user);
        return redirectToList();
    }

    private String redirectToList(){
        logger.info("Redirect to users list");
        return "redirect:/users";
    }

    private User createUser(){
        return userService.create();
    }
}
