package org.example.dao;



import org.example.entidades.Consumo;

import java.util.List;

public interface ConsumoDAO {

    List<Consumo> listarConsumo();

    boolean agregarConsumo(Consumo consumo);

    boolean eliminarConsumo(int id);

    boolean actualizarConsumo(Consumo consumo);

}
