package org.example.complementos.animaciones.stack;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import org.example.complementos.animaciones.AnimacionStrategy;


public class Mover implements AnimacionStrategy {

    private int x1;
    private double tiempo;

    public Mover(int x1, double tiempo) {
        this.x1 = x1;
        this.tiempo = tiempo;
    }

    @Override
    public void ejecutar(Node... node) {

        for(Node n: node){
            TranslateTransition tt = new TranslateTransition(Duration.seconds(tiempo), n);
            tt.setToX(x1);
            tt.setInterpolator(Interpolator.LINEAR);
            tt.play();
        }

    }
}
