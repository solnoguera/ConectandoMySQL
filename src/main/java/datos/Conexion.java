package datos;

import java.sql.*;
import javax.sql.DataSource;

public class Conexion {
    /**
     * Establece la conexion y cierra conexiones.
     */
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";
    
    public DataSource getDataSource(){
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl(JDBC_URL);
        bds.setUsername(JDBC_USER);
        bds.setPassword(JDBC_PASSWORD);
        //definimos el tamanio inicial del pool de conexiones
        bds.setInitialSize(5);
    }
    
    public static Connection getConection() throws SQLException{
        return getDataSource().getConnection();
    }
    
    public static void close (Statement stm) throws SQLException{
        stm.close();
    }
    
    public static void close (PreparedStatement stm) throws SQLException{
        stm.close();
    }
    
    public static void close (ResultSet rs) throws SQLException{
       rs.close();
    }
    public static void close (Connection cn) throws SQLException{
       cn.close();
    }
}
