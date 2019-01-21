package com.qf.serviceimpl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.IGoodsDao;

import entity.Goods;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import service.IGoodsService;
import service.ISearchService;

import java.util.List;



@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;
   @Reference
    private ISearchService searchService;
   @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public List<Goods> queryAll() {
        return goodsDao.selectList(null);
    }

    @Override
    @Transactional
    public Goods insert(Goods goods) {
        goodsDao.insert(goods);
        System.out.println(goods);
        searchService.insertIndex(goods);
        rabbitTemplate.convertAndSend("goods_exchange", "",goods);
        System.out.println(goods);
        return goods;
    }
}
