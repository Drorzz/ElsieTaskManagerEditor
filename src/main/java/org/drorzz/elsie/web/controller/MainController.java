package org.drorzz.elsie.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Drorzz on 14.08.2014.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {
    @RequestMapping(method = RequestMethod.GET)
    public final String homeMapping() {
        return "home";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public final String test() {
        return "test";
    }

}
