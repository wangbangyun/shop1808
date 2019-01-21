package com.qf.demo1;

import com.qf.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.Charset;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("qq",false,false,false,null);
        String w="2345";
        channel.basicPublish("","qq",null,w.getBytes("UTF-8"));
        connection.close();
    }
}
