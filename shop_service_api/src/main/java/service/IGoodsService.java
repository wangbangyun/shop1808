package service;


import entity.Goods;

import java.util.List;


public interface IGoodsService {

    List<Goods> queryAll();

    Goods insert(Goods goods);
}
