package entidadesdenegocio;

import java.util.Random;

public class Maestro {
    private int id;
    private String codigo;
    private String nombre;
    private String apellido;
    private String edad;
    private String especialidad;
    private String dui;

    public Maestro(){}

    public Maestro(int id, String codigo, String nombre, String apellido, String edad, String especialidad, String dui) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.especialidad = especialidad;
        this.dui = dui;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public void generarCodigo(){
        String part1 = apellido.length() >= 2 ? apellido.substring(0, 2) : apellido;
        String part2 = dui.length() >= 4 ? dui.substring(0, 4) : dui;
        this.codigo = part1 + part2;
    }
}
