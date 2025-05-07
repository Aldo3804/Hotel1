package org.example.entidades;


import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class Cliente extends Persona{

    private String codigo_cliente;

}
