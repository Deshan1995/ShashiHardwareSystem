package Inventory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author damith
 */

import java.sql.*;
import java.sql.DriverManager;
import javax.swing.*;

public class conect {
    Connection conn = null;
    public static Connection ConnectDb(){
       try{
           Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Timansi Lakshika\\Documents\\NetBeansProjects\\HardwareSystem\\hardwaredb.sqlite");
            System.out.println("Connect");
            
            return conn;
       } catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
           return null;
       }
       
    }
}
