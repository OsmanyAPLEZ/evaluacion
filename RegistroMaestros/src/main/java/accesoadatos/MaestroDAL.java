package accesoadatos;

import entidadesdenegocio.Maestro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaestroDAL {
    public static int guardar(Maestro maestro) throws SQLException {
        int result = 0;
        try {
            String sql = "INSERT INTO Maestros(Codigo, Nombre, Apellido, Edad, Especialidad, Dui) VALUES(?, ?, ?, ?, ?, ?)";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);

            ps.setString(1, maestro.getCodigo());
            ps.setString(2, maestro.getNombre());
            ps.setString(3, maestro.getApellido());
            ps.setString(4, maestro.getEdad());
            ps.setString(5, maestro.getEspecialidad());
            ps.setString(6, maestro.getDui());

            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite modificar un registro existente
    public static int modificar(Maestro maestro) throws SQLException{
        int result = 0;
        try {
            String sql = "UPDATE Maestros SET Codigo = ?,  Nombre = ?, Apellido = ?, Edad = ?, Especialidad = ?, Dui = ? WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, maestro.getCodigo());
            ps.setString(2, maestro.getNombre());
            ps.setString(3, maestro.getApellido());
            ps.setString(4, maestro.getEdad());
            ps.setString(5, maestro.getEspecialidad());
            ps.setString(6, maestro.getDui());
            ps.setInt(7, maestro.getId());

            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite eliminar un registro existente
    public static int eliminar(Maestro maestro) throws SQLException{
        int result = 0;
        try {
            String sql = "DELETE FROM Maestros WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);

            ps.setInt(1, maestro.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que muestra todos los registros de la tabla
    public static ArrayList<Maestro> obtenerTodos() throws SQLException {
        ArrayList<Maestro> lista = new ArrayList<>();
        Maestro maestro;
        try{
            String sql = "SELECT Id, Codigo, Nombre, Apellido, Edad, Especialidad, Dui FROM Maestros";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ResultSet rs = ComunDB.obtenerResultSet(ps);

            while (rs.next()){
                maestro = new Maestro(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6) ,rs.getString(7));
                lista.add(maestro);
            }
            conexion.close();
            ps.close();
            rs.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }

    // método para consultar mediante criterios
    public static ArrayList<Maestro> obtenerDatosFiltrados(Maestro maestro) throws SQLException{
        ArrayList<Maestro> lista = new ArrayList<>();
        Maestro maes;
        try{
            String sql = "SELECT id, codigo, nombre, apellido, edad, especialidad, dui FROM Maestros WHERE id = ? or apellido like'%" + maestro.getApellido() + "%' or dui like'%" + maestro.getDui() + "%'";
            Connection connection = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(connection, sql);
            ps.setInt(1, maestro.getId());
            ResultSet rs = ComunDB.obtenerResultSet(ps);

            while (rs.next()){
                maes = new Maestro(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
                lista.add(maes);
            }
            connection.close();
            ps.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
