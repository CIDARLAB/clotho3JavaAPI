/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.eclipse.jetty.websocket.api.Session;
import org.json.simple.JSONValue;

/**
 *
 * @author prashantvaidyanathan
 */

@Slf4j
public class Clotho implements MessageListener
{
    
    public Object receivedObject;
    private boolean received;
    private boolean successfulResult;
    
    public String requestId;
    public Channel channel;
    
    private Session session;
  
    public Clotho(ClothoConnection clothoConn)
    {
        System.out.println("New Connection established");
        session = clothoConn.session;
        clothoConn.clothoSocket.addMessageListener(this);
    }
    
    private static String getRequestId()
    {
        String reqId = "";
        reqId += System.currentTimeMillis();
        //System.out.println("Generated Req ID :" + reqId);
        return reqId;
    }
    
    public Object createUser(String username,String password){
        Map createUserMap = new HashMap();
        createUserMap.put("username", username);
        createUserMap.put("password", password);
        return createUser(createUserMap);
    }
    
    
    public Object autocomplete(String query) 
    {
        channel = Channel.autocomplete;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", "query=["+query+"]");
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
    }
    
    
    public Object startsWith(Map map) 
    {
        channel = Channel.startsWith;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
    }
    
    
    public Object destroy(Map map) 
    {
        channel = Channel.destroy;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
    }
    
    public Object createUser(Map map) 
    {
        channel = Channel.createUser;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
    }
    
    public Object login(String username,String password){
        Map loginMap = new HashMap();
        loginMap.put("username", username);
        loginMap.put("credentials", password);
        return login(loginMap);
    }
    
    
    public Object grant(Map map) 
    {
        channel = Channel.grant;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
    }
    
    
    
    public Object login(Map map) 
    {
        channel = Channel.login;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
    }
    
    public Object logout() 
    {
        channel = Channel.logout;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", "");
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
    }
    
    public Object queryOne(Map map) 
    {
        channel = Channel.queryOne;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
    }
    
    public Object query(Map map) 
    {
        channel = Channel.query;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
    }
    
    public Object submit(String script) 
    {
        channel = Channel.submit;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", script);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
        
    }
    
    public Object get(String objectId) 
    {
        channel = Channel.get;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", objectId);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
    }
    
    public Object getAll(List<String> objectIdList) 
    {
        channel = Channel.getAll;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", objectIdList);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
        
    }
    
    public Object create(Map map) 
    {
        channel = Channel.create;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
        
    }
    
    public Object createAll(List<Map> map) 
    {
        channel = Channel.createAll;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
        
    }
    
    public Object set(Map map) 
    {
        channel = Channel.set;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
        
    }
    
    
    
    public Object setAll(List<Map> map) 
    {
        channel = Channel.setAll;
        
        received = false;
        successfulResult = false;
        
        requestId = getRequestId();
        Map queryMap = new HashMap();
        queryMap.put("channel", channel.toString());
        queryMap.put("data", map);
        queryMap.put("requestId", requestId);
        
        String queryString = queryString(queryMap);
        return messageExchange(queryString);
        
    }
    
    public Object messageExchange(String queryString){
        Object resultObject = null;
        
        received = false;
        successfulResult = false;
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
            
        try {
            session.getRemote().sendString(queryString);
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        //connection.sendMessage(queryString);
        while((!received) && (elapsedTime <Args.maxTimeOut))
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
            resultObject = receivedObject;
        }
        return resultObject;
    }
    
    public String queryString(Map queryMap) {
        String queryString = ""; 
        try {
            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            queryString = queryStringWriter.toString();
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queryString;
    }
    
    @Override
    public void messageRecieved(OnMessageEvent event) 
    {
        String message = event.getMessage();
        String nullString = null;
        
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
