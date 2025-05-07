package org.example.entidades;


import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trabajador extends Persona{

    private Double sueldo;
    private String acceso;
    private String login;
    private String password;
    private String estado;

}
