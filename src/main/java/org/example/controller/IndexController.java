package org.example.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.complementos.Reloj;
import org.example.complementos.animaciones.Eventos;
import org.example.complementos.animaciones.stack.CambiarColor;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class IndexController {


    @FXML
    private VBox barra_1;

    @FXML
    private Button btn_inicio, btn_cuartos, btn_ventas, btn_reservas, btn_ajustes;

    @FXML
    private StackPane contenedor;

    @FXML
    private Label lblFecha,lblHora;

    private Reloj reloj = new Reloj();

    public void initialize() throws IOException {
        complementos();
        inicio();
        lblFecha.setText(LocalDate.now().toString());
        reloj.mostrarReloj(lblHora);
    }

    @FXML
    private void inicio() throws IOException {
        cargarVistas("/templates/vistas/inicio.fxml");
    }

    @FXML
    private void cuartos() throws IOException {
        cargarVistas("/templates/vistas/habitacion.fxml");
    }

    @FXML
    private void productos() throws IOException {
        cargarVistas("/templates/vistas/productos.fxml");
    }

    @FXML
    private void reservas() throws IOException {
        cargarVistas("/templates/vistas/panelreservas.fxml");
    }

    @FXML
    private void ajustes() throws IOException {
        cargarVistas("/templates/vistas/ajustes.fxml");
    }


    public void cargarVistas(String ruta) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(ruta)));
        contenedor.getChildren().removeAll();
        contenedor.getChildren().setAll(fxml);
    }

    public void complementos() {
        Eventos eventos =  new Eventos();
        eventos.HoverCambiarColor(Color.web("#255c57"),Color.web("#34817a"),0.3,btn_ajustes,btn_cuartos,btn_ventas,btn_reservas,btn_inicio);
    }
}
