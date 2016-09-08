/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;

import java.net.URI;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;


/**
 *
 * @author prashantvaidyanathan
 */
@Slf4j
public class ClothoConnection {
    public WebSocketClient wsClient;
    public static ClothoWebSocket clothoSocket;
    private Future<Session> fut;
    
    public Session session;
    
    public ClothoConnection(String clothoURI)
    {
        clothoSocket = new ClothoWebSocket();
        
        try {
            URI uri = new URI(clothoURI);
            SslContextFactory factory = new SslContextFactory(true);
            wsClient = new WebSocketClient(factory);
            wsClient.setMaxTextMessageBufferSize(Args.maxTextSize);
            wsClient.setMaxBinaryMessageBufferSize(Args.maxTextSize);
           
            
            wsClient.start();
            fut = wsClient.connect(clothoSocket, uri);
            
            session = fut.get();
            session.getPolicy().setMaxBinaryMessageBufferSize(Args.maxTextSize);
            session.getPolicy().setMaxBinaryMessageSize(Args.maxTextSize);
            session.getPolicy().setMaxTextMessageBufferSize(Args.maxTextSize);
            session.getPolicy().setMaxTextMessageSize(Args.maxTextSize);
//                    .get(10, TimeUnit.SECONDS);
            
        } catch (Exception ex) {
            Logger.getLogger(ClothoConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public ClothoConnection(String clothoURI, long maxTimeOut)
    {
        Args.maxTimeOut = maxTimeOut;
        clothoSocket = new ClothoWebSocket();
        
        try {
            URI uri = new URI(clothoURI);
            SslContextFactory factory = new SslContextFactory(true);
            wsClient = new WebSocketClient(factory);
            wsClient.setMaxTextMessageBufferSize(Args.maxTextSize);
            wsClient.setMaxBinaryMessageBufferSize(Args.maxTextSize);
            wsClient.start();
            
            fut = wsClient.connect(clothoSocket, uri);
            
            session = fut.get();
            session.getPolicy().setMaxBinaryMessageBufferSize(Args.maxTextSize);
            session.getPolicy().setMaxBinaryMessageSize(Args.maxTextSize);
            session.getPolicy().setMaxTextMessageBufferSize(Args.maxTextSize);
            session.getPolicy().setMaxTextMessageSize(Args.maxTextSize);
//                    .get(10, TimeUnit.SECONDS);
            
        } catch (Exception ex) {
            Logger.getLogger(ClothoConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public void closeConnection()
    {
        System.out.println("Closing Connection");
        try {
            session.close();
            wsClient.stop();
        } catch (Exception ex) {
            Logger.getLogger(ClothoConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
