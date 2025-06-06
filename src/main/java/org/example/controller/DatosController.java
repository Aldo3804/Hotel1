package org.example.controller;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DatosController {

    private static DatosController instance;


    private int idPersona;
    private String nombre;
    private String apellido;


    private DatosController(){

    }

    public static DatosController getInstance(){
        if(instance==null){
            instance = new DatosController();
        }
        return instance;
    }

    public void cerrarSesion() {
        instance = null;
    }


}
