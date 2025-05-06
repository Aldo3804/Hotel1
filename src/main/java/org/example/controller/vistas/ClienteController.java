package org.example.demo.controller.vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.demo.complementos.alertas.AlertaFactory;
import org.example.demo.complementos.animaciones.Eventos;
import org.example.demo.complementos.animaciones.stack.Acercar;
import org.example.demo.entidades.Cliente;
import org.example.demo.logica.ClienteImplDAO;
import org.example.demo.servicio.ClienteServicio;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteController {

    @FXML private TextField text_nombre, text_apellidos, text_documento, text_telefono, text_correo, text_buscar, text_registros;
    @FXML private ComboBox<String> combo_doc;
    @FXML private Button btn_nuevo, btn_guardar, btn_eliminar, btn_buscar;
    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> tblNombre, tblApellidos, tblDocumento, tblTelefono,tblCorreo;

    private Cliente cliente = new Cliente();
    private final ClienteServicio clienteServicio = new ClienteServicio(new ClienteImplDAO());
    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();



    public void initialize() {
        configurarTabla();
        cargarCombo();
        cargarClientes();
        tablaClientes.setOnMouseClicked(event -> seleccionarCliente());
        complementos();
    }

    @FXML
    private void nuevo(){
        limpiar();
        cliente = new Cliente();
        btn_guardar.setText("GUARDAR");

    }

    @FXML
    private void guardarCliente() {
        if (camposVacios()) {
            AlertaFactory.Errores("Error", "Los campos están vacíos. Por favor, complételos.");
            return;
        }

        formulario();
        boolean result = clienteServicio.guardarCliente(cliente);
        if (result) {
            AlertaFactory.Informacion("Exito",cliente.getIdpersona()==0 ? "El cliente fue agregado correctamente" :
                    "El cliente fue modificado correctamente");
            cargarClientes();
            nuevo();
        } else {
            AlertaFactory.Errores("Error", "No se pudo guardar el producto.");
        }
    }

    @FXML
    private void eliminarCliente() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            AlertaFactory.Warning("Advertencia", "Debe seleccionar un cliente para eliminar.");
            return;
        }

        if (clienteServicio.eliminarCliente(seleccionado)) {
            AlertaFactory.Informacion("Éxito", "El cliente ha sido eliminado correctamente.");
            cargarClientes();
            nuevo();
        } else {
            AlertaFactory.Errores("Error", "No se pudo eliminar el cliente.");
        }
    }

    @FXML
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


    private void formulario() {

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


    private void buscarClientes() {
        String filtro = text_buscar.getText().toLowerCase();
        if (filtro.isEmpty()) {
            cargarClientes();
            return;
        }

        List<Cliente> filtrados = listaClientes.stream()
                .filter(c -> c.getNombre().toLowerCase().contains(filtro) ||
                        c.getApellidos().toLowerCase().contains(filtro) ||
                        c.getNum_documento().toLowerCase().contains(filtro))
                .collect(Collectors.toList());

        tablaClientes.setItems(FXCollections.observableArrayList(filtrados));
        text_registros.setText(String.valueOf(filtrados.size()));
    }


    private void configurarTabla() {
        tblNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tblDocumento.setCellValueFactory(new PropertyValueFactory<>("num_documento"));
        tblTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tblCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));

        tablaClientes.setItems(listaClientes);
    }


    private void cargarClientes() {
        try {
            List<Cliente> lista = clienteServicio.listarClientes();
            ObservableList<Cliente> observableList = FXCollections.observableArrayList(lista);
            tablaClientes.setItems(observableList);
            actualizarContador();
        } catch (Exception e) {
            AlertaFactory.Errores("Error al cargar los datos.", e.getMessage());
        }
    }

    private void limpiar() {
        text_nombre.clear();
        text_apellidos.clear();
        text_documento.clear();
        text_telefono.clear();
        text_correo.clear();
        combo_doc.setValue(null);
    }

    private boolean camposVacios() {
        return  (text_nombre.getText().isEmpty() || text_apellidos.getText().isEmpty() ||
                combo_doc.getValue() == null || text_documento.getText().isEmpty());
    }

    private void actualizarContador() {
        text_registros.setText(String.valueOf(listaClientes.size()));
    }

    private void cargarCombo() {
        combo_doc.setItems(FXCollections.observableArrayList("DNI", "RUC", "PASAPORTE"));
    }

    private void complementos(){
        Eventos eventos = new Eventos();
        eventos.Hover(new Acercar(0.3,1.1),new Acercar(0.3,1.0),btn_nuevo);
        eventos.Hover(new Acercar(0.3,1.1),new Acercar(0.3,1.0),btn_guardar);
        eventos.Hover(new Acercar(0.3,1.1),new Acercar(0.3,1.0),btn_buscar);
        eventos.Hover(new Acercar(0.3,1.1),new Acercar(0.3,1.0),btn_eliminar);
    }
}
