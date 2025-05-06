package org.example.demo.entidades;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pago {

    private int idpago;
    private int idreserva;
    private String tipo_comprobante;
    private String num_comprobante;
    private Double igv;
    private Double total_pago;
    private Date fecha_emision;


}
