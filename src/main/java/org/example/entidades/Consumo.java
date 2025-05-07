package org.example.entidades;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Consumo {

    private int idconsumo;
    private int idreserva;
    private int idproducto;
    private Double cantidad;
    private Double precio_venta;
    private String estado;


}
