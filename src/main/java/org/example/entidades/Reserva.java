package org.example.entidades;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Reserva {

    private int idreserva;
    private int idhabitacion;
    private int idcliente;
    private int idtrabajador;
    private String tipo_reserva;
    private Date fecha_reserva;
    private Date fecha_ingresa;
    private Date fecha_salida;
    private Double costo_alojamiento;
    private String estado;

}
