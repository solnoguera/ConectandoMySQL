
package test;

import datos.PersonaDaoJDBC;
import domain.PersonaDTO;
import java.util.List;

public class TestManejoPersonasDAO {
    public static void main(String[] args) {
        PersonaDaoJDBC tabla = new PersonaDaoJDBC();
        
        //PROBANDO INSERTAR
//        Persona luqui = new Persona("luca", "ventura", "lucaventura54@gmail.com", "123123123");
//        Persona vicky = new Persona("vicky", "crespo", "vickycrespo@gmail.com", "1122334455");
//        tabla.insertar(luqui);
//        tabla.insertar(vicky);

        //PROBANDO ACTUALIZAR
//        Persona vico = new Persona(5, "vico", "crespox", "lavico@outlook.com", "145236987");
//        tabla.actualizar(vico);
        
        //PROBANDO BORRAR
//      tabla.borrar(new Persona(4));

        //PROBANDO SELECCIONAR
        List<PersonaDTO> personas = tabla.seleccionar();
        for(PersonaDTO persona: personas){
            System.out.println("Persona: " + persona);
        }
    }
}
