package org.example.controller.vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.complementos.alertas.AlertaFactory;
import org.example.complementos.animaciones.Eventos;
import org.example.entidades.Producto;
import org.example.logica.ProductoImplDAO;
import org.example.servicio.ProductoServicio;
import org.example.validaciones.Validaciones;


import java.sql.SQLException;
import java.util.List;

public class ProductoController {


    //----------------------------Definicón de las variables fxml---------------------------------------------//
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

    //------------------------------------Definicón de Clases---------------------------------------------------//

    private Producto producto = new Producto();

    private final ProductoServicio productoServicio = new ProductoServicio(new ProductoImplDAO());

    //-------------------------------------Metodo de Inicio-----------------------------------------------------//
    public void initialize() {
        cargarProductos();
        tablaProducto.setOnMouseClicked(e -> seleccionarProducto());
        cargarCombo();
        complementos();
    }

    //----------------------------------------Metodos FXML-----------------------------------------------------//
    @FXML
    private void Nuevo() {
        limpiar();
        producto = new Producto();
        btn_guardar.setText("GUARDAR");
    }

    @FXML
    private void Guardar() throws SQLException {
        if (camposVacios()) {
            AlertaFactory.Errores("Error", "Se detctaron los campos vacios");
            return;
        }

        obtenerDatos();

        boolean result = productoServicio.guardarProducto(producto);
        try{
            if (result) {
                AlertaFactory.Informacion("Exito",producto.getIdproducto()==0 ? "El producto fue agregado correctamente" :
                        "El producto fue modificado correctamente");
                cargarProductos();
                tablaProducto.refresh();
                Nuevo();
            } else {
                AlertaFactory.Errores("Error", "No se pudo guardar el producto.");
            }
        }catch(Exception e){
            AlertaFactory.Errores("Excepción SQL", e.getMessage());
        }
    }

    @FXML
    private void Eliminar() throws SQLException {

        Producto seleccionado = tablaProducto.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            AlertaFactory.Warning("Advertencia", "Debe seleccionar un producto para eliminar.");
            return;
        }


        if (productoServicio.eliminarProducto(seleccionado)) {
            AlertaFactory.Informacion("Éxito", "El producto ha sido eliminado correctamente.");
            cargarProductos();
            Nuevo();
        } else {
            AlertaFactory.Errores("Error", "No se pudo eliminar el producto.");
        }
    }

    //-------------------------------------Metodos Secundarios-----------------------------------------------------//

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


    private void obtenerDatos() {
        producto.setNombre(text_nombre.getText().trim());
        producto.setDescripcion(text_descripcion.getText().trim());
        producto.setUnidad_medida(combo_medida.getValue());
        producto.setPrecio_venta(Double.parseDouble(text_precio.getText().trim()));
    }

    private void cargarProductos() {
        try {
            List<Producto> lista = productoServicio.listarProductos();
            ObservableList<Producto> observableList = FXCollections.observableArrayList(lista);
            tablaProducto.setItems(observableList);

            tblNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tblDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tblPrecio.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
            tblMedida.setCellValueFactory(new PropertyValueFactory<>("unidad_medida"));

        } catch (Exception e) {
            AlertaFactory.Errores("Error al cargar los datos.", e.getMessage());
        }
    }

    private void cargarCombo() {
        combo_medida.setItems(FXCollections.observableArrayList("unidades","litros","kg","paquetes"));
    }

    private void complementos(){
        Eventos eventos = new Eventos();
        eventos.HoverAcercar(0.3,1.1,btn_buscar,btn_nuevo,btn_eliminar,btn_guardar);
    }

    //--------------------------------------Metodos para Formulario---------------------------------------------//

    private void limpiar(){
        text_nombre.setText("");
        text_precio.setText("");
        text_descripcion.setText("");
        combo_medida.setValue(null);

    }

    private boolean camposVacios(){
        return text_nombre.getText().isEmpty() || text_precio.getText().isEmpty() || text_descripcion.getText().isEmpty() || combo_medida.getValue().isEmpty() ;
    }

}
