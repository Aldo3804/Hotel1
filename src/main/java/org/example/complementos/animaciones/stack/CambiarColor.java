package org.example.demo.complementos.animaciones.stack;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.demo.complementos.animaciones.AnimacionStrategy;

public class CambiarColor implements AnimacionStrategy {

    private final Color colorInicial;
    private final Color colorFinal;
    private final double tiempo;

    public CambiarColor(Color colorInicial, Color colorFinal, double tiempo) {
        this.colorInicial = colorInicial;
        this.colorFinal = colorFinal;
        this.tiempo = tiempo;
    }



    @Override
    public void ejecutar(Node node) {

        if (node instanceof Region region) {
            Timeline timeline = new Timeline();
            int pasos = 60;
            for (int i = 0; i <= pasos; i++) {
                double t = i / (double) pasos;
                double tiempoFrame = t * tiempo;

                Color colorInterpolado = colorInicial.interpolate(colorFinal, t);
                String estilo = "-fx-background-color: " + toRgbString(colorInterpolado) + ";";

                KeyFrame keyFrame = new KeyFrame(Duration.seconds(tiempoFrame),
                        e -> region.setStyle(estilo));
                timeline.getKeyFrames().add(keyFrame);
            }
            timeline.play();
        }



    }

    private static String toRgbString(Color c) {
        return "rgb("
                + (int)(c.getRed() * 255) + ","
                + (int)(c.getGreen() * 255) + ","
                + (int)(c.getBlue() * 255) + ")";
    }

}
