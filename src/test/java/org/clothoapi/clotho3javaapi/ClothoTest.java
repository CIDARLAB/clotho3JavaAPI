/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prashantvaidyanathan
 */
public class ClothoTest {
    
    public ClothoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void query()
    {
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        Map createPart = new HashMap();
        createPart.put("name","createdPart");
        createPart.put("sequence","atataagcgcaaa");
        createPart.put("schema","org.clothocad.testapi");
        createPart.put("id", "testAPIquery");
        
        clothoObject.create(createPart);
        
        Map map = new HashMap();
        map.put("id","testAPIquery");
        
        Object ret = clothoObject.queryOne(map);
        assertEquals(((JSONObject)ret).get("name").toString(),"createdPart");
        assertEquals(((JSONObject)ret).get("id").toString(),"testAPIquery");
        assertEquals(((JSONObject)ret).get("schema").toString(),"org.clothocad.testapi");
        assertEquals(((JSONObject)ret).get("sequence").toString(),"atataagcgcaaa");
        conn.closeConnection();
    }
    
    
    @Test
    public void multipleConnectionTest()
    {
        ClothoConnection conn1 = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject1 = new Clotho(conn1);
        ClothoConnection conn2 = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject2 = new Clotho(conn2);
        
        
        Map createPart1 = new HashMap();
        createPart1.put("name","createdPartconn1");
        createPart1.put("sequence","atataagcgcaaa");
        createPart1.put("schema","org.clothocad.testapi");
        createPart1.put("id", "testAPImultConn");
        
        clothoObject1.create(createPart1);
        
        
        Map map = new HashMap();
        map.put("id","testAPImultConn");
        
        Object ret = clothoObject2.queryOne(map);
        assertEquals(((JSONObject)ret).get("name").toString(),"createdPartconn1");
        assertEquals(((JSONObject)ret).get("id").toString(),"testAPImultConn");
        assertEquals(((JSONObject)ret).get("schema").toString(),"org.clothocad.testapi");
        assertEquals(((JSONObject)ret).get("sequence").toString(),"atataagcgcaaa");
        
        conn1.closeConnection();
        conn2.closeConnection();
    }
    
    
    @Test
    public void get()
    {
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        Map createPart = new HashMap();
        createPart.put("name","createdPartget");
        createPart.put("sequence","atataagcgcaaa");
        createPart.put("schema","org.clothocad.testapi");
        createPart.put("id", "testAPIget");
        
        clothoObject.create(createPart);
        
        Object ret = clothoObject.get("testAPIget");
        assertEquals(((JSONObject)ret).get("name").toString(),"createdPartget");
        assertEquals(((JSONObject)ret).get("id").toString(),"testAPIget");
        assertEquals(((JSONObject)ret).get("schema").toString(),"org.clothocad.testapi");
        assertEquals(((JSONObject)ret).get("sequence").toString(),"atataagcgcaaa");
        conn.closeConnection();
    }
    
    
    
}