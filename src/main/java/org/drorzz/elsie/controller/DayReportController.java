package org.drorzz.elsie.controller;

import org.drorzz.elsie.domain.DayReport;
import org.drorzz.elsie.service.DayReportService;
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

import java.util.List;

@Controller
@RequestMapping(value = "/dayReports")
public class DayReportController {
    private final static Logger logger = LoggerFactory.getLogger(DayReportController.class);

    private final static String dayReportIdEditMask = "[1-9]+|new";

    private DayReportService dayReportService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDayReportService(DayReportService dayReportService) {
        this.dayReportService = dayReportService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String dayReportList(Model model) {
        List<DayReport> dayReportList = dayReportService.getAll();

        logger.info("Day reports list size: {}.", dayReportList.size());

        model.addAttribute("dayReportList", dayReportList);

        return "dayReportList";
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String wrongDayReport() {
        return redirectToList();
    }

    @RequestMapping(value = "/{pathId:"+ dayReportIdEditMask +"}", method = RequestMethod.GET)
    public String dayReportById(Model model, @PathVariable(value = "pathId") String pathId) {
        DayReport dayReport;
        if (pathId.toLowerCase().equals("new")){
            dayReport = createDepartment();
        }else{
            try{
                Integer intId = Integer.valueOf(pathId);
                dayReport = dayReportService.getById(intId);
            }catch(NumberFormatException e){
                return redirectToList();
            }
        }
        if(dayReport == null){
            return redirectToList();
        }
        logger.info("Day report id: {}.", dayReport.getId());

        model.addAttribute("dayReport", dayReport);
        model.addAttribute("userList", userService.getAll());

        return "dayReport";
    }

    @RequestMapping(value = "/{pathId:"+ dayReportIdEditMask +"}", method = RequestMethod.POST)
    public String departmentSave(@ModelAttribute("dayReport") DayReport dayReport) {
        logger.info("Save day report. Id: {}", dayReport.getId());
        dayReportService.save(dayReport);
        return redirectToList();
    }

    private String redirectToList(){
        logger.info("Redirect to day report list");
        return "redirect:/dayReports";
    }

    private DayReport createDepartment(){
        return dayReportService.create();
    }
}
