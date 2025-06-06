package org.example.logica;

import org.example.conexion.Conexion;

public class ConexionImpl {

    private Conexion conexion;
    private static ConexionImpl instance;

    private ConexionImpl(){

    }


    public static ConexionImpl getInstance(){

        if(instance == null){
            instance = new ConexionImpl();
        }
        return instance;

    }

}
