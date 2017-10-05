
package hms.DBClass;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DELL-PC
 */
public class DBconnection {
    
    
    public static Connection connect(){
        
        Connection conn = null;
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb", "root", "");
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
 
        
        return conn;
    
    }
    
}
