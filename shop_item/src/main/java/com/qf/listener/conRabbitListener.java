package com.qf.listener;

import entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(queues ="goods_queue")
public class conRabbitListener {
    @Autowired
    private Configuration configuration;
    @RabbitHandler
    public  void handler(Goods goods) throws IOException {
        System.out.println(goods);

        Map<String,Goods> data=new HashMap<>();
        data.put("goods",goods);
        String path = this.getClass().getResource("/static/page/").getPath()+goods.getId()+".html";
        try(
                Writer out = new FileWriter(path);
        ) {
            //准备商品详情的模板
            Template template = configuration.getTemplate("goods.ftl");
            //生成静态页
            template.process(data, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
