package org.example.complementos.animaciones;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import org.example.complementos.animaciones.stack.Acercar;
import org.example.complementos.animaciones.stack.CambiarColor;

public class Eventos {



    public void HoverFactory(AnimacionStrategy entrada, AnimacionStrategy salida, Node node) {

        node.setOnMouseEntered(event -> entrada.ejecutar(node));
        node.setOnMouseExited(event -> salida.ejecutar(node));

    }


    public void HoverAcercar(double tiempo,double tamanio,Node... node) {
        for (Node n : node) {
            HoverFactory(new Acercar(tiempo,tamanio),new Acercar(tiempo,1.0),n);
        }
    }

    public void HoverCambiarColor(Color color1,Color color2,double tiempo,Node... node) {

        for (Node n : node) {
            HoverFactory(new CambiarColor(color1,color2,tiempo),new CambiarColor(color2,color1,tiempo),n);
        }

    }




}
