package com.qf.shop_back1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {


    @RequestMapping("/topage/{page}")
    public String topage(@PathVariable("page") String page){
        return page;
    }
}
