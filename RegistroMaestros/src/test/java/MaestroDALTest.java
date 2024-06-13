import accesoadatos.MaestroDAL;
import entidadesdenegocio.Maestro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class MaestroDALTest {
    @Test
    public void guardarTest() throws SQLException {
        Maestro teacher = new Maestro();
        teacher.setCodigo("PL23007");
        teacher.setNombre("Juan Carlos");
        teacher.setApellido("Pérez López");
        teacher.setEdad("30");
        teacher.setEspecialidad("Software");
        teacher.setDui("06462233-5");

        int esperado = 1;
        int actual = MaestroDAL.guardar(teacher);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void modificarTest() throws SQLException{
        Maestro teacher = new Maestro();
        teacher.setId(2);
        teacher.setCodigo("PL23007");
        teacher.setNombre("Juan Carlos");
        teacher.setApellido("Jimenez Alvarez");
        teacher.setEdad("30");
        teacher.setEspecialidad("Software");
        teacher.setDui("06462233-5");

        int esperado = 1;
        int actual = MaestroDAL.modificar(teacher);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void eliminarTest() throws SQLException{
        Maestro teacher = new Maestro();
        teacher.setId(2);

        int esperado = 1;
        int actual = MaestroDAL.eliminar(teacher);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerTodosTest() throws SQLException{
        int actual = MaestroDAL.obtenerTodos().size();
        Assertions.assertNotEquals(0, actual);
    }

    @Test
    public void obtenerDatosFiltradosTest() throws SQLException{
        Maestro teacher = new Maestro();
        teacher.setDui("06462233-5");
        teacher.setId(0);
        teacher.setApellido("");

        int actual = MaestroDAL.obtenerDatosFiltrados(teacher).size();
        Assertions.assertNotEquals(0, actual);
    }
}
