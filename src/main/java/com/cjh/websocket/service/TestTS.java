package com.cjh.websocket.service;

import com.cjh.websocket.WebsocketApplication;
import com.cjh.websocket.controller.MyWebSocket;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.SetUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @program: websocket
 * @description: 测试推送
 * @author: soulx
 * @create: 2019-08-15 09:25
 **/
@Service
public class TestTS {
    @Resource
    private MyWebSocket myWebSocket;

    public void send(){
        try {
            myWebSocket.sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
