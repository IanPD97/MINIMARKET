
package testdb;

import java.sql.*;

public class Conectar {
    Connection cn;
    
    public Connection conexion()
    {
       try{
        Class.forName("com.mysql.jdbc.Driver");
        cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MINIMARKET","root","root");
        System.out.println("Conexión Establecida");
    }catch(ClassNotFoundException | SQLException e){
        System.out.println(e.getMessage());
        
    }return cn;
}
    Statement createStatement(){
        throw new UnsupportedOperationException("No Soportado");
    }
}