package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import entity.Goods;
import entity.Page;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import service.ISearchService;


import java.util.ArrayList;
import java.util.List;
@Service
public class SearchServiceImpl implements ISearchService {
    @Autowired
    private SolrClient solrClient;
    @Override
    public Page<Goods> queryByIndex(String keyword ,String currentPage) {
        SolrQuery solrQuery = new SolrQuery();
        Page<Goods> page=new Page<>();

          solrQuery.setRows(2);

        if(currentPage==null||currentPage.trim().equals("")){
            solrQuery.setStart(0);
            page.setCurrentPage(1);
        }else{
            solrQuery.setStart((Integer.parseInt(currentPage)-1)*solrQuery.getRows());
            page.setCurrentPage(Integer.parseInt(currentPage));
        }

        if (keyword == null || keyword.trim().equals("")) {
            solrQuery.setQuery("*:*");
        } else {
            solrQuery.setQuery("gtitle:" + keyword + " || ginfo:" + keyword);
        }

        List<Goods> goodsList = new ArrayList<>();
        try {
            QueryResponse query = solrClient.query(solrQuery);
            SolrDocumentList results = query.getResults();
            int totalcount= (int) results.getNumFound();
            int totalpage=totalcount%solrQuery.getRows()==0?totalcount/solrQuery.getRows():totalcount/solrQuery.getRows()+1;

            page.setTotalPage(totalpage);
//            long numFound = results.getNumFound();
            System.out.println(results);
            for (SolrDocument document : results) {
                String id = (String) document.get("id");
                String gtitle = (String) document.get("gtitle");
                String ginfo = (String) document.get("ginfo");
                float gprice = (float) document.get("gprice");
                int gcount = (int) document.get("gcount");
                String gimage = (String) document.get("gimage");

                Goods goods = new Goods(
                        Integer.parseInt(id),
                        gtitle,
                        ginfo,
                        gcount,
                        0,
                        0,
                        gprice,
                        gimage
                );

                goodsList.add(goods);
                System.out.println(goodsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        page.setGlist(goodsList);
        return page;
    }



    @Override
    public int insertIndex(Goods goods) {
        SolrInputDocument solrDocument = new SolrInputDocument();
        solrDocument.setField("id", goods.getId());
        solrDocument.setField("gtitle", goods.getTitle());
        solrDocument.setField("ginfo", goods.getGinfo());
        solrDocument.setField("gimage", goods.getGimage());
        solrDocument.setField("gcount", goods.getGcount());
        solrDocument.setField("gprice", goods.getPrice());

        try {
            solrClient.add(solrDocument);
            solrClient.commit();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}
