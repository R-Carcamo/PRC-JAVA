
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAO implements CRUD{
    
    Conexion cn=new Conexion();
    Connection con = null;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
    Persona p = new Persona();
    PreparedStatement preparedStatement = null;
    
    @Override
    public List listar() {
        ArrayList<Persona>list=new ArrayList<>();
        String sql="Select * from dato_usuario";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next())
            {
                Persona per = new Persona();
                per.setIdUser(rs.getInt("userId"));
                per.setDni(rs.getInt("id"));
                per.setTitulo(rs.getString("title"));
                per.setCuerpo(rs.getString("body"));
                list.add(per);
                
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Persona list(int id) {
        ArrayList<Persona>list=new ArrayList<>();
        String sql="Select * from dato_usuario where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next())
            {
                //Persona per = new Persona();
                p.setIdUser(rs.getInt("userId"));
                p.setDni(rs.getInt("id"));
                p.setTitulo(rs.getString("title"));
                p.setCuerpo(rs.getString("body"));
                
            }
        } catch (Exception e) {
        }
        return p;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean edit(Persona per) {
        //String sql= "insert into usuarios.dato_usuario(userId, id, title, body) "
        //        + "values('"+per.getIdUser()+"','"+per.getDni()+"','"+per.getTitulo()+"','"+per.getCuerpo()+"')";
        String sql = "update dato_usuario set id=?, title=?, body=? where userId=?";
        con=cn.getConnection();
        try {
            
            preparedStatement = con.prepareStatement(sql);
            //preparedStatement.setString(1, String.valueOf(per.getIdUser()));
            preparedStatement.setString(1, String.valueOf(per.getDni()));
            preparedStatement.setString(2, per.getTitulo());
            preparedStatement.setString(3, per.getCuerpo());
            preparedStatement.setInt(4, per.getIdUser());
            int resultado = preparedStatement.executeUpdate();
            
            
            //con=cn.getConnection();
            //ps=con.prepareStatement(sql);
            //ps.executeUpdate();
        } catch (Exception e) {
        }finally{
            if (preparedStatement!=null)
            {
                try {
                    preparedStatement.close();
                    con.close();  
                } catch (SQLException ex) {
                    Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
        }
        
        return false;
                
    }

    @Override
    public boolean add(Persona per) {
        String sql = "insert into dato_usuario(id, title, body) values(?,?,?)";
        con=cn.getConnection();
        try {
            
            preparedStatement = con.prepareStatement(sql);
            //preparedStatement.setString(1, String.valueOf(per.getIdUser()));
            preparedStatement.setString(1, String.valueOf(per.getDni()));
            preparedStatement.setString(2, per.getTitulo());
            preparedStatement.setString(3, per.getCuerpo());
            int resultado = preparedStatement.executeUpdate();
            
            
            //con=cn.getConnection();
            //ps=con.prepareStatement(sql);
            //ps.executeUpdate();
        } catch (Exception e) {
        }finally{
            if (preparedStatement!=null)
            {
                try {
                    preparedStatement.close();
                    con.close();  
                } catch (SQLException ex) {
                    Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
        }//To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
