/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern;

import ru.sfedu.tavern.model.entities.OurUser;
import ru.sfedu.tavern.model.entities.PlatformUser;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.Entity;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sfedu.tavern.database.DbConnection;
import ru.sfedu.tavern.database.DbMetaTables;
import ru.sfedu.tavern.dataproviders.CsvAPI;

/**
 *
 * @author entropy
 */
public class TavernTest {
    
    public TavernTest() {
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

    /**
     * Test of main method, of class Tavern.
     */
    @Test
    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        Tavern.main(args);            
            
            try {
                DbConnection instence = DbConnection.getInstance();
    //            String row = "'Admin', 'e3afed0047b08059d0fada10f400c1e5','test@mail.ru', '" + System.currentTimeMillis() + '\'';
    //            System.out.println(row);
    //            instence.execQuery("Insert into our_user(login, password_hash, email, last_act) values(" + row  + ")");
                // SLECT OUR USER
                ArrayList<ArrayList<Object>> res =  instence.execSelectState(DbMetaTables.OUR_USER);
                ArrayList<Entity> userList = new ArrayList();
                res.forEach( (args)->userList.add( new OurUser(args) ) );
    
                // SELECT PLATFORMS
                res =  instence.execSelectState(DbMetaTables.PLATFORM);
                ArrayList<Entity> platformList = new ArrayList();
                res.forEach( (args)->platformList.add( new Platform(args) ) );
                
                // SELECT PLATFORMS USERS
                res =  instence.execSelectState(DbMetaTables.PLATFORM_USER);
                ArrayList<Entity> platformUsersList = new ArrayList();
                res.forEach( (args)->platformUsersList.add( new PlatformUser(args) ) );
                
                // SELECT MESSSAGES
                res =  instence.execSelectState(DbMetaTables.MESSAGE);
                ArrayList<Entity> messagesList = new ArrayList();
                res.forEach( (args)->messagesList.add( new Message(args) ) );
                
                // SELECT TEST
                instence.execSelectState(DbMetaTables.TEST);
                instence.closeConnection();
                
                userList.forEach( (val)->System.out.println(val.toString()) );
                platformList.forEach( (val)->System.out.println(val.toString()) );
                platformUsersList.forEach( (val)->System.out.println(val.toString()) );
                messagesList.forEach( (val)->System.out.println(val.toString()) );
                
                // CSV API TEST
                CsvAPI csv = new CsvAPI();
                csv.insert(userList);
                csv.insert(platformList);
                csv.insert(platformUsersList);
                csv.insert(messagesList);
                
                try {
                    
//                    Optional<OurUser> test1 = Optional.ofNullable((OurUser) csv.getObjectByID(1, ClassType.OURUSER).get().get(0));
//                    Platform test2 = (Platform) csv.getObjectByID(1, ClassType.PLATFORM).get().get(0); 
//                    PlatformUser test3 = (PlatformUser) csv.getObjectByID(1, ClassType.PLATFORMUSER).get().get(0);
//                    Message test4 = (Message) csv.getObjectByID(1, ClassType.MESSAGE).get().get(0);
//                    System.out.println("\n\n\n\n\n\n\n\n\n\n================= CSV =================");
//                    System.out.println(test1.toString());
//                    System.out.println(test1.get().toString());
//                    System.out.println(test2.toString());
//                    System.out.println(test3.toString());
//                    System.out.println(test4.toString());
                } catch ( Exception ex ) {
                    System.err.println(ex);
                }
                
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
