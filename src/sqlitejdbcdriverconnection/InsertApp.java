package sqlitejdbcdriverconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author rodrigo
 */
public class InsertApp {
    
    public Connection connection(){
        
        String url = "jdbc:sqlite:/home/rodrigo/NetBeansProjects/SQLiteJDBCDriverConnection/src/db/test.db";
        
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }
    
    public void insert(String name, double capacity){
        String sql = "INSERT INTO miTabla (name, capacity) VALUES (?,?)";
        
        try (Connection conn = this.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        InsertApp app = new InsertApp();
        
        app.insert("Raw Material", 3000);
        app.insert("Semifinished Goods", 4000);
        app.insert("Finished Goods", 5000);
    }
}
