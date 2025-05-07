package org.example.controller.vistas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class PanelReservas {


    @FXML
    private StackPane contenedor;

    public void initialize() throws IOException {
        cargarVistas("/templates/vistas/reservas.fxml");
    }

    @FXML
    private void reserva() throws IOException{
        cargarVistas("/templates/vistas/reservas.fxml");
    }

    @FXML
    private void clientes() throws IOException{
        cargarVistas("/templates/vistas/clientes.fxml");
    }

    public void cargarVistas(String ruta) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(ruta)));
        contenedor.getChildren().removeAll();
        contenedor.getChildren().setAll(fxml);
    }
}
