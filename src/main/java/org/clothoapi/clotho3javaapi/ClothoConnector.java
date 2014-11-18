/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;

import java.net.URI;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.eclipse.jetty.websocket.WebSocketClientFactory;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.json.simple.JSONObject;

/**
 *
 * @author prashantvaidyanathan
 */
public class ClothoConnector {
    public static void main(String[] args) {
        
        String clothoURI = "wss://localhost:8443/websocket";
        //String clothoURI = "ws://echo.websocket.org";
        //String clothoURI = "wss://localhost:9090/websocket"; 
        SimpleEchoSocket socket = new SimpleEchoSocket();
        WebSocketClientFactory factory = new WebSocketClientFactory();
        ClothoWebSocket clothoSocket = new ClothoWebSocket();
        WebSocket.Connection serverConnection;
        //WebSocketClient client = new WebSocketClient();
        try {
            /*client.start();
            URI clothoConnectURI = new URI(clothoURI);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            
            JSONObject obj = new JSONObject();
            obj.put("channel", "say");
            obj.put("data", "Hello Clotho!!");
            obj.put("requestId", "1");
            SimpleEchoSocket socket = new SimpleEchoSocket();
            
            client.connect(socket, clothoConnectURI, request);
            System.out.println("Trying to connect!");
            socket.awaitClose(5, TimeUnit.SECONDS);*/
            factory.start();
            org.eclipse.jetty.websocket.WebSocketClient wsClient = factory.newWebSocketClient();
            URI uri = new URI(clothoURI);
            //Future fut = wsClient.open(uri, clothoWebSocket);
            Future fut = wsClient.open(uri, clothoSocket);
            serverConnection = (Connection) fut.get(10, TimeUnit.SECONDS);
            String jsonString = "{\"channel\":\"create\",\"data\":{\"name\":\"partMeh\",\"sequence\":\"ATGC\"},\"requestId\":1416307480825}";
            //serverConnection.sendMessage("Hello Clotho!");
            serverConnection.sendMessage(jsonString);
            serverConnection = (Connection) fut.get(10, TimeUnit.SECONDS);
            
                    
            
        } catch (Exception ex) {
            Logger.getLogger(ClothoConnector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                factory.stop();
            } catch (Exception ex) {
                Logger.getLogger(ClothoConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
    }
}