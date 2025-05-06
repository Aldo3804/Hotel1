package org.example.demo.entidades;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Producto {

    private int idproducto;
    private String nombre;
    private String descripcion;
    private String unidad_medida;
    private Double precio_venta;

}
