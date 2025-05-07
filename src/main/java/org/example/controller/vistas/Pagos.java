package org.example.controller.vistas;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Pagos {


    @FXML
    private Button btn_salir;


    public void initialize() {

        btn_salir.setOnAction(e -> {
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            currentStage.close();
        });

    }
}
