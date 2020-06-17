package com.wjian.study.config.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import javax.annotation.Resource;
import org.springframework.cloud.stream.messaging.Source;

import java.util.UUID;

/**
 * @author wangjian
 * @date 2020/6/15 0015 12:18
 */
@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProvider
{
    @Resource
    private MessageChannel output; // 消息发送管道

    public String send()
    {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: "+serial);
        return null;
    }
}

