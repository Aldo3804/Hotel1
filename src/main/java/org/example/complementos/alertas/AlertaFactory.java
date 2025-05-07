package org.example.complementos.alertas;

import javafx.scene.control.Alert;

public class AlertaFactory {

    public static Alert crearAlerta(Alert.AlertType tipo, String titulo, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        return alerta;
    }

    public static void Confirmacion(String titulo, String mensaje){
        crearAlerta(Alert.AlertType.CONFIRMATION, titulo, mensaje).showAndWait();
    }

    public static void Errores(String titulo, String mensaje){
        crearAlerta(Alert.AlertType.ERROR, titulo, mensaje).showAndWait();
    }

    public static void Informacion(String titulo, String mensaje){
        crearAlerta(Alert.AlertType.INFORMATION, titulo, mensaje).showAndWait();
    }

    public static void Warning(String titulo, String mensaje){
        crearAlerta(Alert.AlertType.WARNING, titulo, mensaje).showAndWait();
    }

}
