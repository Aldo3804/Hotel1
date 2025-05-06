package org.example.demo.complementos;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CargarFXML {

    private String ruta;
    private double minAncho;
    private double minAlto;
    private boolean noMostrarOpcionesVentana;

    public CargarFXML(String ruta, double minAncho, double minAlto,boolean noMostrarOpcionesVentana) {
        this.ruta = ruta;
        this.minAncho = minAncho;
        this.minAlto = minAlto;
        this.noMostrarOpcionesVentana = noMostrarOpcionesVentana;
    }

    public void Cargar() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ruta));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        if (noMostrarOpcionesVentana) {
            stage.initStyle(StageStyle.UNDECORATED);
        }
        stage.setMinWidth(minAncho);
        stage.setMinHeight(minAlto);
        stage.setScene(scene);
        stage.show();

    }

}
