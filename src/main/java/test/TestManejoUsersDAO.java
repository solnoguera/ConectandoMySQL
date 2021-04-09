
package test;

import datos.*;
import domain.UserDTO;
import java.sql.*;
import static datos.Conexion.*;
import java.util.List;

public class TestManejoUsersDAO {
    
    public static void main(String[] args) {
    
    UserDTO sol = new UserDTO("solcha", "no");
    UserDTO euge = new UserDTO("eugenita", "si");
    
    //PROBAMOS TRANSACCION
    
    Connection conexion = null;
    
    try{
        conexion = getConection();
        
        if (conexion.getAutoCommit()){
            conexion.setAutoCommit(false);
        }
        //Utilizamos variable de interfaz para que sea lo mas generica posible, no importa la tecnologia q esta implementando.
        UserDAO tabla = new UserDaoJDBC(conexion);
        
        tabla.insertar(euge);
        tabla.actualizar(sol);
        
        conexion.commit();
        
    }catch(SQLException e){
        conexion.rollback();
    }
    
    //PRUEBA SELECCIONAR
    List<UserDTO> users = tabla.seleccionar();
    for(UserDTO usuario : users){
        System.out.println("User: " + usuario);
    }
    
    }
        
    
    
}
