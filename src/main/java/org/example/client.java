package org.example;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class client {
    private static Logger logger = LoggerFactory.getLogger(client.class);
    public static WebSocketClient client;
    public static void main(String[] args) {
        try {
            client = new WebSocketClient(new URI("ws://localhost:8887"),new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("握手成功");
                }

                @Override
                public void onMessage(String msg) {
                    System.out.println("收到消息=========="+msg);
                    if(msg.equals("over")){
                        client.close();
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("链接已关闭");
                }

                @Override
                public void onError(Exception e){
                    e.printStackTrace();
                    System.out.println("发生错误已关闭");
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        client.setConnectionLostTimeout(0);
        client.connect();
        //System.out.println(client.getDraft());
//        while(!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)){
//            System.out.println("正在连接...");
//        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(client.getReadyState());
        System.out.println("hello world");
        //连接成功,发送信息
        client.send("哈喽,连接一下啊");
        client.close();
    }


}
