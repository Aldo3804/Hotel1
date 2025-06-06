package org.example.controller.vistas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.complementos.CargarFXML;
import org.example.controller.DatosController;


import java.io.IOException;

public class Reservas {


    @FXML
    private Button btn_consumo,btn_pago;

    public void initialize() {

        DatosController dc = DatosController.getInstance();
        System.out.println(dc.getNombre());

    }


    @FXML
    public void cargarPagos() throws IOException {

        CargarFXML fxml = new CargarFXML("/templates/vistas/pagos.fxml",963.0,529.0,true);
        fxml.Cargar();
    }

    @FXML
    public void cargarConsumo() throws IOException {
        CargarFXML fxml = new CargarFXML("/templates/vistas/consumo.fxml",963.0,529.0,true);
        fxml.Cargar();
    }

}
