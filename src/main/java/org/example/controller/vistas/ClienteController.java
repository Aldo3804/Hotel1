package org.example.controller.vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.complementos.alertas.AlertaFactory;
import org.example.complementos.animaciones.Eventos;
import org.example.entidades.Cliente;
import org.example.logica.ClienteImplDAO;
import org.example.servicio.ClienteServicio;


import java.sql.SQLException;
import java.util.List;

public class ClienteController {


    //----------------------------Definicón de las variables fxml---------------------------------------------//
    @FXML
    private TextField text_nombre, text_apellidos, text_documento, text_telefono, text_correo, text_buscar, text_registros;

    @FXML
    private ComboBox<String> combo_doc;

    @FXML
    private Button btn_nuevo, btn_guardar, btn_eliminar, btn_buscar;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> tblNombre, tblApellidos, tblDocumento, tblTelefono, tblCorreo;


    //----------------------------------Definicón de las clases-------------------------------------------------//
    private Cliente cliente = new Cliente();

    private final ClienteServicio clienteServicio = new ClienteServicio(new ClienteImplDAO());


    //-----------------------------------Metodo para la inicialización--------------------------------------------//
    public void initialize() {
        cargarCombo();
        cargarClientes();
        tablaClientes.setOnMouseClicked(event -> seleccionarCliente());
        complementos();
    }

    //------------------------------------------Metodos con llaves FXML--------------------------------------------//
    @FXML
    private void Nuevo(){
        limpiar();
        cliente = new Cliente();
        btn_guardar.setText("GUARDAR");
    }

    @FXML
    private void Guardar() {

        if (camposVacios()) {
            AlertaFactory.Errores("Error", "Los campos están vacíos. Por favor, complételos.");
            return;
        }

        cargarDatos();

        try{
            boolean result = clienteServicio.guardarCliente(cliente);
            if (result) {
                AlertaFactory.Informacion("Exito",cliente.getIdpersona()==0 ? "El cliente fue agregado correctamente" :
                        "El cliente fue modificado correctamente");
                tablaClientes.refresh();
                cargarClientes();
                Nuevo();
            } else {
                AlertaFactory.Errores("Error", "No se pudo guardar el producto.");
            }
        }catch(SQLException e){
            AlertaFactory.Errores("SQL Error", "Error al guardar el producto."+e.getMessage());
        }


    }

    @FXML
    private void Eliminar() throws SQLException {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            AlertaFactory.Warning("Advertencia", "Debe seleccionar un cliente para eliminar.");
            return;
        }

        if (clienteServicio.eliminarCliente(seleccionado)) {
            AlertaFactory.Informacion("Éxito", "El cliente ha sido eliminado correctamente.");
            tablaClientes.refresh();
            cargarClientes();
            Nuevo();
        } else {
            AlertaFactory.Errores("Error", "No se pudo eliminar el cliente.");
        }
    }

    //-----------------------------------------------Metodos Secundarios--------------------------------------------//
    private void seleccionarCliente() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            cliente = seleccionado;
            text_nombre.setText(seleccionado.getNombre());
            text_apellidos.setText(seleccionado.getApellidos());
            text_correo.setText(seleccionado.getEmail());
            text_documento.setText(seleccionado.getNum_documento());
            text_telefono.setText(seleccionado.getTelefono());
            combo_doc.setValue(seleccionado.getTipo_documento());
            btn_guardar.setText("EDITAR");
        }
    }


    private void cargarDatos() {

        cliente.setNombre(text_nombre.getText().trim());
        cliente.setApellidos(text_apellidos.getText().trim());
        cliente.setEmail(text_correo.getText().trim());  // <-- Corregido
        cliente.setTelefono(text_telefono.getText().trim());
        cliente.setTipo_documento(combo_doc.getValue());
        cliente.setNum_documento(text_documento.getText().trim());

        // Solo asignar un nuevo código si es un cliente nuevo
        if (cliente.getIdpersona() == 0) {
            cliente.setCodigo_cliente("CL - " + System.currentTimeMillis());
        }
    }


    private void cargarClientes() {
        List<Cliente> lista = clienteServicio.listarClientes();
        ObservableList<Cliente> observableList = FXCollections.observableArrayList(lista);
        tablaClientes.setItems(observableList);

        tblNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tblDocumento.setCellValueFactory(new PropertyValueFactory<>("num_documento"));
        tblTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tblCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
    }


    private void cargarCombo() {
        combo_doc.setItems(FXCollections.observableArrayList("DNI", "RUC", "PASAPORTE"));
    }

    private void complementos(){
        Eventos eventos = new Eventos();
        eventos.HoverAcercar(0.3,1.1,btn_buscar,btn_guardar,btn_eliminar,btn_nuevo);
    }

    //------------------------------------------Metodos con Formularios--------------------------------------------//
    private boolean camposVacios() {
        return  (text_nombre.getText().isEmpty() || text_apellidos.getText().isEmpty() ||
                combo_doc.getValue() == null || text_documento.getText().isEmpty());
    }

    private void limpiar() {
        text_nombre.clear();
        text_apellidos.clear();
        text_documento.clear();
        text_telefono.clear();
        text_correo.clear();
        combo_doc.setValue(null);
    }

}
