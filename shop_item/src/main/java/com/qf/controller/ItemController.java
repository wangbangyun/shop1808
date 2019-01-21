package com.qf.controller;

import com.google.gson.Gson;
import entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private Configuration configuration;
    @RequestMapping("/createhtml")
    @ResponseBody
    public String  createHtml(@RequestBody Goods goods) throws IOException, TemplateException {

        Map<String,Goods> data=new HashMap<>();
        data.put("goods",goods);
        Template template = configuration.getTemplate("goods,ftl");
        String path = this.getClass().getResource("/static/page").getPath()+goods.getId()+"html";
        FileWriter path2=  new FileWriter(path);
        template.process(data, path2);
        path2.close();
        return "ok";
    }
}
//       Map map =new HashMap();
//        String[] names={"ff","df","df"};
//        map.put("names",names);
//        map.put("name","freemaker");
//        map.put("age",20);
//        map.put("time",new Date());
//        Writer out=new FileWriter("C:\\Users\\wangbangyun\\hello.html");
//        template.process(map,out);
//        out.close();