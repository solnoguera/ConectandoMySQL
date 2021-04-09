
package datos;

import domain.UserDTO;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    /**
     * Interface para los metodos DAO sin importar la tecnologia que se use, en nuestro caso usamos JDBC.
     */
    
    public List<UserDTO> seleccionar() throws SQLException;
    
    public int insertar(UserDTO usuario) throws SQLException;
    
    public int actualizar(UserDTO usuario) throws SQLException;
    
    public int borrar(UserDTO usuario) throws SQLException;
    
}
