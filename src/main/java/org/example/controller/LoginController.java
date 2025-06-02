package org.example.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.complementos.CargarFXML;
import org.example.complementos.animaciones.Eventos;
import org.example.complementos.animaciones.stack.Acercar;
import org.example.complementos.animaciones.stack.CambiarColor;


import java.io.IOException;

public class LoginController {

    @FXML
    private Button btn_ingresar,btn_salir;

    @FXML
    private TextField text_usuario;

    @FXML
    private PasswordField text_contra;


    public void initialize() {
        complementario();
    }

    @FXML
    private void salir(){
        System.exit(0);
    }
    @FXML
    private void ingresar(ActionEvent event) throws IOException {

        CargarFXML fxml = new CargarFXML("/templates/index.fxml",1100,680,false);
        fxml.Cargar();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

    }

    private void complementario(){

        Eventos eventos = new Eventos();

        eventos.HoverAcercar(0.3,1.1,btn_ingresar);

        eventos.HoverCambiarColor(Color.web("#ffffff"),Color.web("#9f1c1c"),0.3,btn_salir);


    }


}
