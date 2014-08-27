package org.drorzz.elsie.tme.web.controller;

import org.drorzz.elsie.tme.domain.DayReport;
import org.drorzz.elsie.tme.domain.User;
import org.drorzz.elsie.tme.service.DayReportService;
import org.drorzz.elsie.tme.service.UserService;
import org.drorzz.utils.web.PageHolder;
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

    private final static int PAGE_SIZE = 20;

    private UserService userService;
    private PageHolder pageHolder;

    public DayReportController() {
        super("dayReportList", "dayReport");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private List<User> usersList(){
        List<User> userList = userService.getAll();
        logger.info("User list size: {}.", userList.size());
        return userList;
    }

    private PageHolder getPageHolder(){
        return new PageHolder(entityService.getCount().intValue(),PAGE_SIZE);
    }

    @Override
    protected List<DayReport> entityList(int page) {
        logger.info("Getting page: {}.", page);
        pageHolder = getPageHolder();
        pageHolder.setPage(page-1);
        return entityService.getPage(pageHolder.getFirstElementOnPage(),
                                        pageHolder.getPageSize(), "date", "desc");
    }

    @Override
    protected void addEntityListMappingModelAttributes(Model model, List<DayReport> entityList, int page) {
        if(pageHolder != null) {
            model.addAttribute("pageHolder", pageHolder);
        }
    }

    @Override
    protected void addEntityByIdMappingModelAttributes(Model model, DayReport dayReport) {
        model.addAttribute("userList", usersList());
    }
}
