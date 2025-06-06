package org.example.controller;


import com.mysql.cj.log.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.complementos.CargarFXML;
import org.example.complementos.alertas.AlertaFactory;
import org.example.complementos.animaciones.Eventos;
import org.example.complementos.animaciones.stack.Acercar;
import org.example.complementos.animaciones.stack.CambiarColor;
import org.example.dao.TrabajadorDAO;
import org.example.entidades.Persona;
import org.example.entidades.Trabajador;
import org.example.logica.TrabajadorImplDAO;
import org.example.servicio.TrabajadorServicio;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginController {

    @FXML
    private Button btn_ingresar,btn_salir;

    @FXML
    private TextField text_usuario;

    @FXML
    private PasswordField text_contra;

    private final TrabajadorServicio  trabajadorServicio = new TrabajadorServicio(new TrabajadorImplDAO());



    public LoginController(){

    }


    public void initialize() {
        complementario();
    }

    @FXML
    private void salir(){
        System.exit(0);
    }

    @FXML
    private void ingresar(ActionEvent event) throws SQLException, IOException {

        Trabajador trabajador = trabajadorServicio.iniciarSesion(text_usuario.getText(),text_contra.getText());

        if(trabajador != null){


            Persona per = trabajadorServicio.buscarPorId(trabajador.getIdpersona());
            DatosController dc = DatosController.getInstance();
            dc.setIdPersona(per.getIdpersona());
            dc.setNombre(per.getNombre());
            dc.setApellido(per.getApellidos());

            cargarVistaIndex(event);

        }else{
            AlertaFactory.Errores("Error de Autenticación","Las credenciales son incorrectas, vuelvalas a ingresar");
        }

    }

    private void complementario(){

        Eventos eventos = new Eventos();

        eventos.HoverAcercar(0.3,1.1,btn_ingresar);

        eventos.HoverCambiarColor(Color.web("#ffffff"),Color.web("#9f1c1c"),0.3,btn_salir);


    }

    public void cargarVistaIndex(ActionEvent event) throws IOException {
        CargarFXML fxml = new CargarFXML("/templates/index.fxml",1100,680,false);
        fxml.Cargar();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

    }

}
