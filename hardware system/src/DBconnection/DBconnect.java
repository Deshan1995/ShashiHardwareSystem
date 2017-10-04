/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.AbstractAction.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ACERPC
 */
public class DBconnect {

    
    Connection conn=null;
    
    public static Connection connectDb(){
    try{
        Class.forName("org.sqlite.JDBC");
        Connection conn=DriverManager.getConnection("jdbc:sqlite:E:\\sliit\\itp\\hardware system\\hardwaredb.sqlite");
        //JOptionPane.showMessageDialog(null,"");
        return conn;
    }
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,e);
        return null;
    }
    
    }
    
    
    
}
