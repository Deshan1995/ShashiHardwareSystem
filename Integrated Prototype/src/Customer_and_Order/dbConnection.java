/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer_and_Order;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Bhanuka Yapa
 */
public class dbConnection {
    
    public static Connection open(){
        Connection conn  = null;
        
        try{
            Class.forName("org.sqlite.JDBC");
            conn = (Connection) DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Timansi Lakshika\\Documents\\NetBeansProjects\\HardwareSystem\\hardwaredb.sqlite");
        }
        catch(Exception e){
            System.out.println(e);
            
        }
        
        return conn;
    }
    
    
}
