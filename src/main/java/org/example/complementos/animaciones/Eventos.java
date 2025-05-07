package org.example.complementos.animaciones;

import javafx.scene.Node;

public class Eventos {



    public void Hover(AnimacionStrategy entrada,AnimacionStrategy salida,Node node) {

        node.setOnMouseEntered(event -> entrada.ejecutar(node));
        node.setOnMouseExited(event -> salida.ejecutar(node));

    }



}
