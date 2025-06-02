package org.example.controller.vistas;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.complementos.alertas.AlertaFactory;
import org.example.complementos.animaciones.Eventos;
import org.example.entidades.Habitacion;
import org.example.logica.HabitacionImplDAO;
import org.example.servicio.HabitacionServicio;
import org.example.validaciones.Validaciones;

import java.sql.SQLException;
import java.util.List;

public class HabitacionController {


    //----------------------------Definicón de las variables fxml---------------------------------------------//
    @FXML
    private TextField text_num, text_precio;

    @FXML
    private TextArea text_detalles;

    @FXML
    private ComboBox<String> combo_piso, combo_estado, combo_tipo;

    @FXML
    private TableView<Habitacion> tblHabitaciones;

    @FXML
    private TableColumn<Habitacion, String> tblNumero, tblPiso, tblDetalles, tblEstado, tblTipo;

    @FXML
    private TableColumn<Habitacion, Double> tblPrecio;

    @FXML
    private Button btn_guardar,btn_nuevo,btn_eliminar,btn_buscar,btn_reporte;

    //-----------------------------------------Definición de Clases---------------------------------------------//

    private Habitacion habitacion = new Habitacion();

    private final HabitacionServicio habitacionServicio = new HabitacionServicio(new HabitacionImplDAO());


    //-------------------------------Metodos para la inicialización--------------------------------------------//

    public void initialize() {
        cargarCombos();
        cargarHabitaciones();
        tblHabitaciones.setOnMouseClicked(event -> seleccionarHabitacion());
        complementos();
    }

    //--------------------------------------Metodos con llave FXML---------------------------------------------//

    @FXML
    private void Nuevo(){
        limpiar();
        habitacion = new Habitacion();
        btn_guardar.setText("GUARDAR");
    }


    @FXML
    private void Guardar() {

        if (camposVacios()) {
            AlertaFactory.Errores("Error", "Se detectaron los campos vacíos");
            return;
        }

        obtenerDatos();

        try {
            boolean result = habitacionServicio.guardar(habitacion);
            if (result) {
                AlertaFactory.Informacion("Éxito", habitacion.getIdhabitacion() == 0
                        ? "La habitación fue agregada correctamente."
                        : "La habitación fue actualizada correctamente.");
                cargarHabitaciones();
                tblHabitaciones.refresh();
                Nuevo();
            } else {
                AlertaFactory.Errores("Error", "No se pudo guardar la habitación.");
            }
        } catch (SQLException e) {
            AlertaFactory.Errores("Excepción SQL", e.getMessage());
        }

    }

    @FXML
    private void Eliminar() throws SQLException {

        Habitacion seleccionada = tblHabitaciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            AlertaFactory.Errores("Selección requerida", "Debes seleccionar una habitación para eliminar.");
            return;
        }

        if (habitacionServicio.eliminar(seleccionada)) {
            AlertaFactory.Informacion("Éxito", "La habitación ha sido eliminado correctamente.");
            cargarHabitaciones();
            Nuevo();
        }else{
            AlertaFactory.Errores("Error", "No se pudo eliminar la habitación");
        }

    }

    //--------------------------------------Metodos Secundarios---------------------------------------------//

    private void seleccionarHabitacion() {
        Habitacion seleccionada = tblHabitaciones.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            habitacion = seleccionada;
            text_num.setText(seleccionada.getNumero());
            text_precio.setText(String.valueOf(seleccionada.getPrecio_diario()));
            text_detalles.setText(seleccionada.getDetalles());
            combo_piso.setValue(seleccionada.getPiso());
            combo_estado.setValue(seleccionada.getEstado());
            combo_tipo.setValue(seleccionada.getTipo_habitacion());
            btn_guardar.setText("EDITAR");
        }
    }


    private void obtenerDatos() {
        habitacion.setNumero(text_num.getText());
        habitacion.setDetalles(text_detalles.getText());
        habitacion.setPrecio_diario(Double.parseDouble(text_precio.getText()));
        habitacion.setPiso(combo_piso.getValue());
        habitacion.setEstado(combo_estado.getValue());
        habitacion.setTipo_habitacion(combo_tipo.getValue());
    }


    private void cargarHabitaciones() {
        try {
            List<Habitacion> lista = habitacionServicio.listar();
            ObservableList<Habitacion> datos = FXCollections.observableArrayList(lista);
            tblHabitaciones.setItems(datos);

            tblNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tblPiso.setCellValueFactory(new PropertyValueFactory<>("piso"));
            tblDetalles.setCellValueFactory(new PropertyValueFactory<>("detalles"));
            tblPrecio.setCellValueFactory(new PropertyValueFactory<>("precio_diario"));
            tblEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
            tblTipo.setCellValueFactory(new PropertyValueFactory<>("tipo_habitacion"));

        } catch (SQLException e) {
            AlertaFactory.Errores("Error al cargar datos", e.getMessage());
        }
    }


    public void cargarCombos() {
        combo_piso.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7"));
        combo_estado.setItems(FXCollections.observableArrayList("Disponible", "Indisponible"));
        combo_tipo.setItems(FXCollections.observableArrayList("Familiar", "Normal", "Matrimonial"));
    }


    private void complementos(){
        Eventos eventos = new Eventos();
        eventos.HoverAcercar(0.3,1.1,btn_buscar,btn_reporte,btn_eliminar,btn_nuevo,btn_guardar);
    }

    //--------------------------------------Metodos para Formulario---------------------------------------------//

    private void limpiar(){
        text_num.setText("");
        text_precio.setText("");
        text_detalles.setText("");
        combo_piso.setValue(null);
        combo_estado.setValue(null);
    }

    private boolean camposVacios(){
        return text_num.getText().isEmpty() || text_detalles.getText().isEmpty() || text_precio.getText().isEmpty() || combo_piso.getValue().isEmpty() || combo_estado.getValue().isEmpty();
    }

}
