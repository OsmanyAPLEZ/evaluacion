package logicadenegocio;

import accesoadatos.MaestroDAL;
import entidadesdenegocio.Maestro;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaestroBL {
    public int guardar(Maestro maestro) throws SQLException {
        return MaestroDAL.guardar(maestro);
    }

    public int modificar(Maestro maestro) throws SQLException{
        return MaestroDAL.modificar(maestro);
    }

    public int eliminar(Maestro maestro) throws SQLException{
        return MaestroDAL.eliminar(maestro);
    }

    public ArrayList<Maestro> obtenerTodos() throws SQLException{
        return MaestroDAL.obtenerTodos();
    }

    public ArrayList<Maestro> obtenerDatosFiltrados(Maestro maestro) throws SQLException{
        return MaestroDAL.obtenerDatosFiltrados(maestro);
    }
}
