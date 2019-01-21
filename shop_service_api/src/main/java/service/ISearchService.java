package service;

import entity.Goods;
import entity.Page;

public interface ISearchService {
    Page<Goods> queryByIndex(String keyword, String currentPage);
    int insertIndex(Goods goods);
}
