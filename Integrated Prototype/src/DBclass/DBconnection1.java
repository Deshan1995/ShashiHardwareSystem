
package DBclass;

//import com.mysql.jdbc.Connection;
import java.sql.*;

/**
 *
 * @author DELL-PC
 */
public class DBconnection1 {
    
    
    public static Connection connect(){
        
        Connection conn = null;
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            conn=(Connection) DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Timansi Lakshika\\Documents\\NetBeansProjects\\HardwareSystem\\hardwaredb.sqlite");
            System.out.println("connected");
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
 
        
        return conn;
    
    }
    
}
