package com.qf.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    private  static ConnectionFactory factory;
    static {
        factory=new ConnectionFactory();

        factory.setHost("192.168.126.128");
        factory.setPort(5672);
        factory.setUsername("wby");
        factory.setPassword("wby");
        factory.setVirtualHost("/wby");

    }

    public  static Connection getConnection(){
        Connection connection=null;
        try {
              connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return  connection;
    }
}
