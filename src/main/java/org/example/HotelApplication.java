package org.example;


import javafx.application.Application;
import javafx.stage.Stage;
import org.example.complementos.CargarFXML;


public class HotelApplication extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        CargarFXML cargarFXML =  new CargarFXML("/templates/login.fxml",347,536,true);
        cargarFXML.Cargar();

    }

    public static void main(String[] args) {
        try {
            Application.launch(HotelApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}