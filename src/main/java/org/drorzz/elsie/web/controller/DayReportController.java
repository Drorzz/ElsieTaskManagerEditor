package org.drorzz.elsie.web.controller;

import org.drorzz.elsie.domain.DayReport;
import org.drorzz.elsie.domain.User;
import org.drorzz.elsie.service.DayReportService;
import org.drorzz.elsie.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/dayReports")
public class DayReportController extends AbstractEntityController<DayReport, DayReportService>  {
    private final static Logger logger = LoggerFactory.getLogger(DayReportController.class);

    private UserService userService;

    public DayReportController() {
        super("dayReportList", "dayReport");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private List<User> usersList(){
        List<User> userList = userService.getAll();
        logger.info("User list size: {}.",userList.size());
        return userList;
    }

    @Override
    protected List<DayReport> entityList() {
        return entityService.getAllWithOrderDesc("date");
    }

    @Override
    protected void addEntityListMappingModelAttributes(Model model) {

    }

    @Override
    protected void addEntityByIdMappingModelAttributes(Model model, DayReport dayReport) {
        model.addAttribute("userList", usersList());
        model.addAttribute("title", dayReport.getUser().getFullName() + " - " + dayReport.getDate());
    }
}
