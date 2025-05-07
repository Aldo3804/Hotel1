package org.example.controller.vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.complementos.alertas.AlertaFactory;
import org.example.complementos.animaciones.Eventos;
import org.example.complementos.animaciones.stack.Acercar;
import org.example.entidades.Producto;
import org.example.logica.ProductoImplDAO;
import org.example.servicio.ProductoServicio;


import java.util.List;

public class ProductoController {

    @FXML
    private TableView<Producto> tablaProducto;

    @FXML
    private TableColumn<Producto, String> tblNombre, tblDescripcion, tblMedida;

    @FXML
    private TableColumn<Producto, Double> tblPrecio;

    @FXML
    private TextField text_nombre, text_precio;

    @FXML
    private ComboBox<String> combo_medida;

    @FXML
    private TextArea text_descripcion;

    @FXML
    private Button btn_guardar, btn_eliminar, btn_buscar, btn_nuevo;

    private Producto producto = new Producto();
    private final ProductoServicio productoServicio = new ProductoServicio(new ProductoImplDAO());

    public void initialize() {
        configurarTabla();
        cargarCombo();
        cargarDatos();
        tablaProducto.setOnMouseClicked(e -> seleccionarProducto());
        complementos();
    }

    @FXML
    private void nuevo() {
        limpiar();
        producto = new Producto();
        btn_guardar.setText("GUARDAR");
    }

    @FXML
    private void guardar() {
        if (camposVacios()) {
            AlertaFactory.Errores("Error", "Los campos están vacíos. Por favor, complételos.");
            return;
        }

        formulario();
        boolean result = productoServicio.guardarProducto(producto);
        if (result) {
            AlertaFactory.Informacion("Exito",producto.getIdproducto()==0 ? "El producto fue agregado correctamente" :
                    "El producto fue modificado correctamente");
            cargarDatos();
            nuevo();
        } else {
            AlertaFactory.Errores("Error", "No se pudo guardar el producto.");
        }
    }

    @FXML
    private void eliminar() {
        Producto seleccionado = tablaProducto.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            AlertaFactory.Warning("Advertencia", "Debe seleccionar un producto para eliminar.");
            return;
        }

        if (productoServicio.eliminarProducto(seleccionado)) {
            AlertaFactory.Informacion("Éxito", "El producto ha sido eliminado correctamente.");
            cargarDatos();
            nuevo();
        } else {
            AlertaFactory.Errores("Error", "No se pudo eliminar el producto.");
        }
    }

    @FXML
    private void seleccionarProducto() {
        Producto seleccionado = tablaProducto.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            producto = seleccionado;
            text_nombre.setText(seleccionado.getNombre());
            text_descripcion.setText(seleccionado.getDescripcion());
            combo_medida.setValue(seleccionado.getUnidad_medida());
            text_precio.setText(String.valueOf(seleccionado.getPrecio_venta()));
            btn_guardar.setText("EDITAR");
        }
    }

    private void cargarCombo() {
        ObservableList<String> medidas = FXCollections.observableArrayList("unidades", "kg", "litros", "paquete");
        combo_medida.setItems(medidas);
    }

    private void limpiar() {
        text_nombre.clear();
        text_precio.clear();
        text_descripcion.clear();
        combo_medida.setValue(null);  // No borrar los items, solo limpiar la selección
    }

    private boolean camposVacios() {
        return text_nombre.getText().trim().isEmpty() ||
                text_precio.getText().trim().isEmpty() ||
                combo_medida.getValue() == null ||
                text_descripcion.getText().trim().isEmpty();
    }

    private void formulario() {
        producto.setNombre(text_nombre.getText().trim());
        producto.setDescripcion(text_descripcion.getText().trim());
        producto.setUnidad_medida(combo_medida.getValue());
        producto.setPrecio_venta(Double.parseDouble(text_precio.getText().trim()));
    }

    private void cargarDatos() {
        try {
            List<Producto> lista = productoServicio.listarProductos();
            ObservableList<Producto> observableList = FXCollections.observableArrayList(lista);
            tablaProducto.setItems(observableList);
        } catch (Exception e) {
            AlertaFactory.Errores("Error al cargar los datos.", e.getMessage());
        }
    }

    private void configurarTabla() {
        tblNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tblPrecio.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
        tblMedida.setCellValueFactory(new PropertyValueFactory<>("unidad_medida"));
    }

    private void complementos(){
        Eventos eventos = new Eventos();
        eventos.Hover(new Acercar(0.3,1.1),new Acercar(0.3,1.0),btn_buscar);
        eventos.Hover(new Acercar(0.3,1.1),new Acercar(0.3,1.0),btn_eliminar);
        eventos.Hover(new Acercar(0.3,1.1),new Acercar(0.3,1.0),btn_nuevo);
        eventos.Hover(new Acercar(0.3,1.1),new Acercar(0.3,1.0),btn_guardar);
    }



}
