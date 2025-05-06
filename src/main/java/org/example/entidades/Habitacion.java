package org.example.demo.entidades;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Habitacion {

    private int idhabitacion;
    private String numero;
    private String piso;
    private String detalles;
    private Double precio_diario;
    private String estado;
    private String tipo_habitacion;

}
