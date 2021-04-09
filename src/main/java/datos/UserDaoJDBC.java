package datos;

import static datos.Conexion.*;
import domain.UserDTO;
import java.sql.*;
import java.util.*;

public class UserDaoJDBC implements UserDAO {

    /**
     * ADAPTADO PARA USAR CON TRANSACCIONES.
     */
    private Connection conexionTransaccional;
    private static final String SQL_SELECT = "SELECT id_user, username, password FROM user";
    private static final String SQL_INSERT = "INSERT INTO user(username, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE user SET username = ?, password = ? WHERE id_user = ?";
    private static final String SQL_DELETE = "DELETE FROM user WHERE id_user = ?";

    public UserDaoJDBC() {
    }

    public UserDaoJDBC(Connection conexion) {
        this.conexionTransaccional = conexion;
    }

    public List<UserDTO> seleccionar() throws SQLException {

        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;
        List<UserDTO> users = new ArrayList<UserDTO>();

        try {
            conexion = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            instruccion = conexion.prepareStatement(SQL_SELECT);
            resultado = instruccion.executeQuery();

            while (resultado.next()) {
                int idUser = resultado.getInt("id_user");
                String username = resultado.getString("username");
                String password = resultado.getString("password");

                UserDTO usuario = new UserDTO(idUser, username, password);

                users.add(usuario);
            }

        } finally {
            close(resultado);
            close(instruccion);
            if (this.conexionTransaccional == null) {
                close(conexion);
            }
        }

        return users;
    }

    public int insertar(UserDTO user) throws SQLException {

        Connection conexion = null;
        PreparedStatement instruccion = null;
        int registros = 0;

        try {
            conexion = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            instruccion = conexion.prepareStatement(SQL_INSERT);
            instruccion.setString(1, user.getUsername());
            instruccion.setString(2, user.getPassword());
            registros = instruccion.executeUpdate();

        } finally {
            close(instruccion);
            if (this.conexionTransaccional == null) {
                close(conexion);
            }

        }

        return registros;
    }

    public int actualizar(UserDTO user) throws SQLException {

        Connection conexion = null;
        PreparedStatement instruccion = null;
        int registros = 0;

        try {
            conexion = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            instruccion = conexion.prepareStatement(SQL_UPDATE);
            instruccion.setString(1, user.getUsername());
            instruccion.setString(2, user.getPassword());
            instruccion.setInt(3, user.getIdUser());

            registros = instruccion.executeUpdate();

        } finally {
            close(instruccion);
            if (this.conexionTransaccional == null) {
                close(conexion);
            }

        }

        return registros;

    }

    public int borrar(UserDTO user) throws SQLException {

        Connection conexion = null;
        PreparedStatement instruccion = null;
        int registros = 0;

        try {
            conexion = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            instruccion = conexion.prepareStatement(SQL_DELETE);
            instruccion.setInt(1, user.getIdUser());

            registros = instruccion.executeUpdate();

        } finally {
            close(instruccion);
            if (this.conexionTransaccional == null) {
                close(conexion);
            }

        }

        return registros;

    }
}
