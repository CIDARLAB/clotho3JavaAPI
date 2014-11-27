/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.eclipse.jetty.websocket.WebSocket;
import org.json.simple.JSONValue;

/**
 *
 * @author prashantvaidyanathan
 */

@Slf4j
public class Clotho implements MessageListener
{
    
    public static Object receivedObject;
    private static boolean received;
    private static boolean successfulResult;
    
    public static String requestId;
    public static Channel channel;
    
    
    private static WebSocket.Connection connection;
  
    public Clotho(ClothoConnection clothoConn)
    {
        System.out.println("New Connection established");
        connection = clothoConn.getServerConnection();
        clothoConn.clothoSocket.addMessageListener(this);
    }
    
    private static String getRequestId()
    {
        String reqId = "";
        reqId += System.currentTimeMillis();
        //System.out.println("Generated Req ID :" + reqId);
        return reqId;
    }
    
    public static Object queryOne(Map map) 
    {
        JSONObject resultObject = null;
        getRequestId();
        channel = Channel.queryOne;
        received = false;
        successfulResult = false;
        try {
            StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map queryMap = new HashMap();
            queryMap.put("channel", channel.toString());
            queryMap.put("data", map);
            queryMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <10))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONObject.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public void messageRecieved(OnMessageEvent event) 
    {
        String message = event.getMessage();
        String nullString = null;
        //System.out.println("This is a message :" + message);
        JSONObject messageObject = JSONObject.fromObject(message);
        try
        {
            if (this.requestId.equals(messageObject.get("requestId").toString())) 
            {
                if (messageObject.get("channel").equals("say")) 
                {

                    JSONObject dataObject = JSONObject.fromObject(messageObject.get("data"));
                    if (dataObject.get("text").equals(nullString)) 
                    {
                        System.out.println("No results found!");
                        received = true;
                        receivedObject = messageObject.get("data");

                    } 
                    else 
                    {
                        System.out.println(dataObject.get("text"));
                    }
                }
                if (messageObject.get("channel").equals(this.channel.toString())) 
                {
                    successfulResult = true;
                    receivedObject = messageObject.get("data");
                    received = true;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error accessing one of the object values");
        }
    }
    
}
