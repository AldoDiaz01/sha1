package sha1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexionDB 
{
    private Connection con = null;
    private static conexionDB cx = null;

    private conexionDB()
    {
        try
        {
            String url = "jdbc:postgresql://localhost:5432/sh1";
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, "postgres", "z1123581321");
            System.out.println("Conexion establecida");
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            Logger.getLogger(conexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean execute(String sql)
    {
        boolean res = false;
        try
        {
            Statement st = con.createStatement();
            st.execute(sql);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(conexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public ResultSet executeQuery(String sql)
    {
        ResultSet res = null;
        try
        {
            Statement st = con.createStatement();
            res = st.executeQuery(sql);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(conexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public static conexionDB getInstance()
    {
        if(cx==null)
            cx = new conexionDB();
        return cx;
    }
    public void Disconect()
    {
        try
        {
            con.close();
            System.out.println("Conexi贸n Terminada");
            
        }
        catch (SQLException ex)
        {
            System.out.println("El siguiente error ha ocurrido: " + ex.getMessage());
        }
    }
}