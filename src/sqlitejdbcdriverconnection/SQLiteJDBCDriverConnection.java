
package sqlitejdbcdriverconnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rodrigo
 */
public class SQLiteJDBCDriverConnection {
    
    public static Connection connect(){
        Connection conn = null;
        
        try {
            String url = "jdbc:sqlite:/home/rodrigo/NetBeansProjects/SQLiteJDBCDriverConnection/src/db/test.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been  established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }
    
    public static void createNewDatabase(String fileName){
        String url = "jdbc:sqlite:/home/rodrigo/NetBeansProjects/SQLiteJDBCDriverConnection/src/db/" + fileName + ".db";
        try (Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.err.println("The driver name is " + meta.getDriverName());
                System.err.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void createNewTable(){
        String sql = "CREATE TABLE IF NOT EXISTS miTabla ( \n" 
                    +"id integer PRIMARY KEY, \n"
                    +"name text NOT NULL, \n"
                    +"capacity real \n"
                    +");";
        try (Connection conn = connect();
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        //connect();
        //createNewDatabase("nuevaBase");
        createNewTable();
    }
    
}
