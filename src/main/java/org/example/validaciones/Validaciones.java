package org.example.validaciones;


import javafx.scene.control.TextField;

public class Validaciones {


    public void limpiarCampos(TextField... text){
        for(TextField f: text){
            f.setText("");
        }
    }


    public boolean validarCamposVacios(TextField... text){

        for(TextField f: text){
            if(f.getText().isEmpty()){
                return false;
            }
        }
        return true;
    }




}
