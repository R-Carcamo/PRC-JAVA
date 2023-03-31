
package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    String respuesta;
    
    public Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios","root","Recv@1996");
        } catch (Exception e){
            System.err.println("Error"+e);
        }
    }
    
    public Connection getConnection(){
        return con;
    }
}
