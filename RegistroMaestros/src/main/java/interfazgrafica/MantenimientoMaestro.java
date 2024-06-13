package interfazgrafica;

import entidadesdenegocio.Maestro;
import logicadenegocio.MaestroBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MantenimientoMaestro extends JFrame {
    private JPanel jpPrincipal;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JTextField txtApellido;
    private JComboBox cbEspecialidad;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JButton btnSalir;
    private JRadioButton rdbId;
    private JRadioButton rdbApellido;
    private JRadioButton rdbDui;
    private JTextField txtCriterio;
    private JButton btnBuscar;
    private JTable jtMaestros;
    private JTextField txtEdad;
    private JTextField txtDui;
    private ButtonGroup criterioBusqueda;

    //intancias propias
    ArrayList<Maestro> listaMaestros;
    Maestro teacher;
    MaestroBL maestroBL = new MaestroBL();

    public static void main(String[] args) throws SQLException {
        new MantenimientoMaestro();
    }

    public MantenimientoMaestro() throws SQLException {
        inicializar();
        actuazilarForm();

        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    actuazilarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                txtCodigo.setEnabled(false);
                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtEdad.setEnabled(true);
                cbEspecialidad.setEnabled(true);
                txtDui.setEnabled(true);


                btnGuardar.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnCancelar.setEnabled(true);
            }
        });

        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                teacher = new Maestro();

                // Obtener las primeras letras del nombre
                String nombre = txtNombre.getText();
                String nombreAbreviado = nombre.substring(0, Math.min(nombre.length(), 1)); // Tomar las primeras 2 letras del nombre

                // Obtener las primeras letras del nombre
                String apellido = txtApellido.getText();
                String apellidoAbreviado = apellido.substring(0, Math.min(apellido.length(), 1)); // Tomar las primeras 2 letras del nombre

                // Obtener las primeras cuatro cifras del DUI
                String dui = txtDui.getText();
                String duiAbreviado = dui.substring(0, Math.min(dui.length(), 4)); // Tomar las primeras 4 cifras del DUI

                // Setear el código con las primeras letras del nombre y las primeras cuatro cifras del DUI
                teacher.setCodigo(nombreAbreviado + apellidoAbreviado + duiAbreviado);

                teacher.setNombre(txtNombre.getText());
                teacher.setApellido(txtApellido.getText());
                teacher.setEdad(txtEdad.getText());
                teacher.setEspecialidad(cbEspecialidad.getSelectedItem().toString());
                teacher.setDui(txtDui.getText());

                try{
                    maestroBL.guardar(teacher);
                    JOptionPane.showMessageDialog(null,"Se almaceno correctamente");
                    actuazilarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                teacher = new Maestro();
                teacher.setId(Integer.parseInt(txtId.getText()));
                teacher.setCodigo(txtCodigo.getText());
                teacher.setNombre(txtNombre.getText());
                teacher.setApellido(txtApellido.getText());
                teacher.setEdad(txtEdad.getText());
                teacher.setEspecialidad(cbEspecialidad.getSelectedItem().toString());
                teacher.setDui(txtDui.getText());

                try{
                    maestroBL.modificar(teacher);
                    JOptionPane.showMessageDialog(null,"Se modifico correctamente");
                    actuazilarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        jtMaestros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = jtMaestros.getSelectedRow();

                txtId.setText(jtMaestros.getValueAt(fila, 0).toString());
                txtCodigo.setText(jtMaestros.getValueAt(fila, 1).toString());
                txtNombre.setText(jtMaestros.getValueAt(fila, 2).toString());
                txtApellido.setText(jtMaestros.getValueAt(fila, 3).toString());
                txtEdad.setText(jtMaestros.getValueAt(fila, 4).toString());
                cbEspecialidad.setSelectedItem(jtMaestros.getValueAt(fila, 5));
                txtDui.setText(jtMaestros.getValueAt(fila, 6).toString());

                txtCodigo.setEnabled(false);
                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtEdad.setEnabled(true);
                cbEspecialidad.setEnabled(true);
                txtDui.setEnabled(true);

                btnNuevo.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
                btnCancelar.setEnabled(true);
                btnEliminar.setEnabled(true);
            }
        });

        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                teacher = new Maestro();

                teacher.setId(Integer.parseInt(txtId.getText()));
                teacher.setCodigo(txtCodigo.getText());
                teacher.setNombre(txtNombre.getText());
                teacher.setApellido(txtApellido.getText());
                teacher.setEdad(txtEdad.getText());
                teacher.setEspecialidad(cbEspecialidad.getSelectedItem().toString());
                teacher.setDui(txtDui.getText());

                // Mostrar un mensaje de confirmación
                int opcion = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el registro?");

                // Verificar la opción seleccionada por el usuario
                if (opcion == JOptionPane.YES_OPTION) {
                    // Si el usuario elige sí, eliminar el registro
                    try {
                        maestroBL.eliminar(teacher);
                        JOptionPane.showMessageDialog(null,"Se elimino correctamente");
                        actuazilarForm();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    // Si el usuario elige no o cancela, no hacer nada
                }
            }
        });
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(txtCriterio.getText().equals("") || (!rdbId.isSelected() &&
                        !rdbApellido.isSelected() && !rdbDui.isSelected()) ){
                    JOptionPane.showMessageDialog(null,
                            "Seleccione un criterio de búsqueda o escriba el valor a buscar");
                }

                teacher = new Maestro();

                if(rdbId.isSelected()){
                    teacher.setId(Integer.parseInt(txtCriterio.getText()));
                    try{
                        llenoTabla(maestroBL.obtenerDatosFiltrados(teacher));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(rdbApellido.isSelected()){
                    teacher.setApellido(txtCriterio.getText());
                    try{
                        llenoTabla(maestroBL.obtenerDatosFiltrados(teacher));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(rdbDui.isSelected()){
                    teacher.setDui(txtCriterio.getText());
                    try{
                        llenoTabla(maestroBL.obtenerDatosFiltrados(teacher));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    void inicializar(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,700);
        setTitle("Control de Maestros");
        setLayout(null);
        setLocationRelativeTo(null);

        criterioBusqueda = new ButtonGroup();
        criterioBusqueda.add(rdbId);
        criterioBusqueda.add(rdbApellido);
        criterioBusqueda.add(rdbDui);

        setContentPane(jpPrincipal); //Se llena el panel con el jpPrincipal
        setVisible(true);
    }

    void llenoTabla(ArrayList<Maestro> maestros){
        Object[] objects = new Object[7]; //Depende de la cantidad de atributos
        listaMaestros = new ArrayList<>();
        String[] encabezado = {"ID", "CODIGO", "NOMBRE", "APELLIDO", "Edad", "Especialidad", "Dui"};
        DefaultTableModel tm = new DefaultTableModel(null, encabezado);
        listaMaestros.addAll(maestros);

        for (Maestro item : listaMaestros){
            objects[0] = item.getId();
            objects[1] = item.getCodigo();
            objects[2] = item.getNombre();
            objects[3] = item.getApellido();
            objects[4] = item.getEdad();
            objects[5] = item.getEspecialidad();
            objects[6] = item.getDui();
            tm.addRow(objects);
        }
        jtMaestros.setModel(tm);
    }

    void actuazilarForm() throws SQLException {
        txtId.setText("");
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        cbEspecialidad.setSelectedIndex(0);
        txtDui.setText("");

        txtId.setEnabled(false);
        txtCodigo.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtEdad.setEnabled(false);
        cbEspecialidad.setEnabled(false);
        txtDui.setEnabled(false);

        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        txtCriterio.setText("");
        criterioBusqueda.clearSelection();

        llenoTabla(maestroBL.obtenerTodos());
    }
}
