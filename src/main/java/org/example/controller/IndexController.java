package org.example.demo.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.example.demo.complementos.animaciones.Eventos;
import org.example.demo.complementos.animaciones.stack.CambiarColor;

import java.io.IOException;
import java.util.Objects;

public class IndexController {


    @FXML
    private VBox barra_1;

    @FXML
    private Button btn_inicio, btn_cuartos, btn_ventas, btn_reservas, btn_ajustes;

    @FXML
    private StackPane contenedor;


    public void initialize() throws IOException {
        complementos();
        inicio();
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

    public void complementos()  {
        animarBotones(btn_inicio);
        animarBotones(btn_cuartos);
        animarBotones(btn_ventas);
        animarBotones(btn_reservas);
        animarBotones(btn_ajustes);
    }

    public void animarBotones(Node node) {
        Eventos eventos =  new Eventos();
        eventos.Hover(new CambiarColor(Color.web("#255c57"),Color.web("#34817a"),0.3),new CambiarColor(Color.web("#34817a"),Color.web("#255c57"),0.3),node);
    }

    public void cargarVistas(String ruta) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(ruta)));
        contenedor.getChildren().removeAll();
        contenedor.getChildren().setAll(fxml);
    }
}
