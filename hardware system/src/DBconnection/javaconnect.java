/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnection;

import Supplier.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Timansi_Lakshika
 */
public class javaconnect {
    
    Connection conn = null;
    public static Connection ConnectDB(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:E:\\sliit\\itp\\hardware system\\hardwaredb.sqlite");
            //JOptionPane.showMessageDialog(null,"Connection Established");
            System.out.println("Connection successful!");
            return conn;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
        //return null;
    }
}
