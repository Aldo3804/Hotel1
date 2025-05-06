package org.example.demo.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Persona {

    private int idpersona;
    private String nombre;
    private String apellidos;
    private String tipo_documento;
    private String num_documento;
    private String direccion;
    private String telefono;
    private String email;

}
