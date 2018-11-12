package com.example.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //connection是socket连接的抽象，并且为我们管理协议版本协商（protocol version negotiation），认证（authentication ）等等事情。
        // 这里我们要连接的消息代理在本地，因此我们将host设为“localhost”。如果我们想连接其他机器上的代理，只需要将这里改为特定的主机名或IP地址。
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //发送之前，我们必须声明消息要发往哪个队列，然后我们可以向队列发一条消息：
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "hellooworld";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("utf-8"));
        System.out.println("发送了");
        channel.close();
        connection.close();
    }
}
