package net.dovale.okta.secure_rest_api;

import com.rabbitmq.client.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RabbitMQConsumerProducer {

    public static void main(String[] args) throws Exception
    {
        Connection conn = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
// "guest"/"guest" by default, limited to localhost connections
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            factory.setHost("127.0.0.1");
            factory.setPort(5672);
            factory.setConnectionTimeout(10);
           
             conn = factory.newConnection();
            System.out.println("Here");

            BufferedReader in
                    = new BufferedReader(new InputStreamReader(System.in));
            String str = in.readLine();
            System.out.println(str);
            Channel channel = conn.createChannel();

            channel.exchangeDeclare("bla", "direct", true);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, "bla", "blaKey");
        }
        finally
        {
            if (conn!=null)
            conn.close();
        }
    }
}
