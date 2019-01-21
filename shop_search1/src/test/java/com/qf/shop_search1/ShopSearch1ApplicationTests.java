package com.qf.shop_search1;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.lang.annotation.Documented;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopSearch1ApplicationTests {
    @Autowired
    private SolrClient solrClient;

    @Test
    public void insert() {
        SolrInputDocument document=new SolrInputDocument();
        document.setField("id","2");
        document.setField("gtitle", "华为笔记本");
        document.setField("ginfo", "国家的荣耀");
        document.setField("gprice", "6000.0");
        document.setField("gimage", "www.baidu.com");
        document.setField("gcount", "1000");
        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void delete() throws IOException, SolrServerException  {
//        solrClient.deleteById("2");
        solrClient.deleteByQuery("*:*");
        solrClient.commit();
    }
    @Test
    public  void  update() throws IOException, SolrServerException {
        SolrInputDocument document=new SolrInputDocument();
             document.setField("id", "1");
            document.setField("gtitle", "华为笔记本");
            document.setField("ginfo", "贵得要死");
            document.setField("gprice", "6000.0");
            document.setField("gprice", "6000.0");
        solrClient.add(document);
        solrClient.commit();
    }
    @Test
    public void query(){
        SolrQuery solrQuery=new SolrQuery();
         solrQuery.setQuery("*:*");
        try {
            QueryResponse query = solrClient.query(solrQuery);
            SolrDocumentList documentList = query.getResults();
            for (SolrDocument document:documentList){
                 String id =(String) document.get("id");
                String  ginfo =(String) document.get("ginfo");
                String  gtitle =(String) document.get("gtitle");
                float  gprice =(float) document.get("gprice");
                System.out.println(id+" "+ginfo+" "+gtitle+" "+gtitle+" "+gprice);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

