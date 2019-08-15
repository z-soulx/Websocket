package com.cjh.websocket.controller;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket服务端
 * @author chen jia hao
 */
@Component
@ServerEndpoint(value = "/myWebSocket")
public class MyWebSocket {

    //用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<MyWebSocket> user = new CopyOnWriteArraySet<MyWebSocket>();

    // 推荐 以唯一id（eg：用户名）为key存储，连接对象，当需要发送指定的人时，可以直接从key中获取。
    private static ConcurrentHashMap<String, MyWebSocket> webSocketSet = new ConcurrentHashMap<String, MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message,Session session) throws IOException {

        //群发消息
        for (MyWebSocket myWebSocket : user) {
            myWebSocket.session.getBasicRemote().sendText(session.getId()+"说："+message);
            //myWebSocket.session.getBasicRemote().sendText("<img src=''/>");
        }
    }

    public void sendMessage() throws IOException {
        for (MyWebSocket myWebSocket : user) {
            RemoteEndpoint.Basic basicRemote = myWebSocket.session.getBasicRemote();
            basicRemote
                    .sendText("说我是发送测试");
            //myWebSocket.session.getBasicRemote().sendText("<img src=''/>");
        }
    }

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId()+" open...");
        this.session = session;
        user.add(this);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        System.out.println(this.session.getId()+" close...");
        user.remove(this);
    }


    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println(this.session.getId()+" error...");
        error.printStackTrace();
    }

}
