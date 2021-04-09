package datos;

import static datos.Conexion.*;
import domain.PersonaDTO;
import java.sql.*;
import java.util.*;

public class PersonaDaoJDBC {

    /**
     * Se encarga del patron de disenio DAO Data access object: SELECCIONAR,
     * ACTUALIZAR, OBTENER, BORRAR de la clase Persona.
     */
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ?"; 
    
    public List<PersonaDTO> seleccionar() {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        List<PersonaDTO> personas = new ArrayList<>();
        try {
            conexion = getConection();
            statement = conexion.prepareStatement(SQL_SELECT);
            resultado = statement.executeQuery();

            while (resultado.next()) {
                int idPersona = resultado.getInt("id_persona");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String email = resultado.getString("email");
                String telefono = resultado.getString("telefono");

                PersonaDTO persona = new PersonaDTO(idPersona, nombre, apellido, email, telefono);

                personas.add(persona);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(resultado);
                close(statement);
                close(conexion);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return personas;
    }

    public int insertar(PersonaDTO persona) {

        Connection conexion = null;
        PreparedStatement instruccion = null;
        int registros = 0;

        try {
            conexion = getConection();
            instruccion = conexion.prepareStatement(SQL_INSERT);
            //TODAVIA NO PUEDO EJECUTAR PORQUE NECESITO COMPLETAR EL INSERT. resultado = instruccion.executeQuery();
            instruccion.setString(1, persona.getNombre());
            instruccion.setString(2, persona.getApellido());
            instruccion.setString(3, persona.getEmail());
            instruccion.setString(4, persona.getTelefono());

            //NO ES EXECUTE QUERY PORQUE ESTA HACIENDO UN UPDATE DE LA BASE DE DATOS, NO CONSULTA SINO AGREGA.
            //Insert, Update y Delete ejecutan este metodo
            registros = instruccion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(instruccion);
                close(conexion);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return registros;

    }

    public int actualizar(PersonaDTO persona) {

        Connection conexion = null;
        PreparedStatement instruccion = null;
        int registros = 0;

        try {
            conexion = getConection();
            instruccion = conexion.prepareStatement(SQL_UPDATE);
            //completar con datos de persona
            instruccion.setString(1, persona.getNombre());
            instruccion.setString(2, persona.getApellido());
            instruccion.setString(3, persona.getEmail());
            instruccion.setString(4, persona.getTelefono());
            instruccion.setInt(5, persona.getIdPersona());

            registros = instruccion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(instruccion);
                close(conexion);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return registros;
    }
    
    public int borrar(PersonaDTO persona){
        
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int registros = 0;
        
        try {
            conexion = getConection();
            instruccion = conexion.prepareStatement(SQL_DELETE);
            instruccion.setInt(1, persona.getIdPersona());
            registros = instruccion.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                close(instruccion);
                close(conexion);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        
        return registros;
        
    }
}
