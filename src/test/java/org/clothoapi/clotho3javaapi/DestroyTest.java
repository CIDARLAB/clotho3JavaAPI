/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clothoapi.clotho3javaapi;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class DestroyTest {
    private static String username1;
    private static String password;
    public DestroyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ClothoConnection conn = new ClothoConnection(TestArgs.clothoLocalAddress);
        Clotho clothoObject = new Clotho(conn);
        username1 = "testDestroy1" + System.currentTimeMillis();
        password = "testPassword";
        
        Map newUserMap1 = new HashMap();
        newUserMap1.put("username", username1);
        newUserMap1.put("password", password);
        
        
        Object res1 = clothoObject.createUser(newUserMap1);
        clothoObject.logout();
        
        conn.closeConnection();
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
    public void destroyTest(){
        ClothoConnection conn = new ClothoConnection(TestArgs.clothoLocalAddress);
        Clotho clothoObject = new Clotho(conn);
        
        System.out.println("Username :: " + username1);
        System.out.println("Password :: " + password);
        
        Map loginMap = new HashMap();
        loginMap.put("username", username1);
        loginMap.put("credentials", password);
        
        Object res = clothoObject.login(loginMap);
        String newObjectName = "destroy" + System.currentTimeMillis();
        Map newObject = new HashMap();
        newObject.put("name", newObjectName);
        newObject.put("schema", "org.clothocad.test.destroy");
        
        String id = (String)clothoObject.create(newObject);
        
        System.out.println("Name of the object " + newObjectName);
        System.out.println("Created Id : " + id);
        
        Object destroyRes = clothoObject.destroy(id);
        
        System.out.println("Destroy Message :: " + destroyRes);
        
        Object getRes = clothoObject.get(id);
        
        assertEquals(getRes,null);
        //System.out.println("Get Res " + getRes);
        
        conn.closeConnection();
    }
    
}
