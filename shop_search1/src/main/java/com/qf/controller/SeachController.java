package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import entity.Goods;
import entity.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ISearchService;

import java.util.List;


@Controller
@RequestMapping("/search")
public class SeachController {
    @Reference
    private ISearchService searchService;
    @RequestMapping("/query")
    public  String search( String currentPage,String keyword, Model model){
        System.out.println("搜索工程"+keyword);
        Page<Goods> page= searchService.queryByIndex(keyword, currentPage);
        page.setUrl("http://localhost:8083/search/query");
        page.setKeyword(keyword);
        model.addAttribute("page",page);
        System.out.println(page);
        return "searchlist";
    }
}
