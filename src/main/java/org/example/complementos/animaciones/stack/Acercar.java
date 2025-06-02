package org.example.complementos.animaciones.stack;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import org.example.complementos.animaciones.AnimacionStrategy;


public class Acercar implements AnimacionStrategy {

    private final double tiempo;
    private final double tamanio;

    public Acercar(double tiempo, double tamanio) {
        this.tiempo = tiempo;
        this.tamanio = tamanio;
    }

    @Override
    public void ejecutar(Node... node) {

        for (Node n: node){
            ScaleTransition st = new ScaleTransition(Duration.seconds(tiempo), n);
            st.setToX(tamanio);
            st.setToY(tamanio);
            st.setInterpolator(Interpolator.LINEAR);
            st.play();
        }

    }
}
