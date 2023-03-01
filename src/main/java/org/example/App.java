package org.example;

import org.java_websocket.client.WebSocketClient;

import javax.xml.ws.WebServiceClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 8887; // 843 flash policy port

        SocketServer s = new SocketServer(port);
        s.start();
        System.out.println("ChatServer started on port: " + s.getPort());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sysin.readLine();
            s.broadcast(in);
            if (in.equals("exit")) {
                s.stop(1000);
                break;
            }
        }
    }

}
