package org.example.complementos;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Reloj {

    public void mostrarReloj(Label lblHora){

        Timeline reloj =  new Timeline(
                new KeyFrame(Duration.seconds(0),e->actualizarHora(lblHora)),
                new KeyFrame(Duration.seconds(1))
        );

        reloj.setCycleCount(Timeline.INDEFINITE);
        reloj.play();

    }

    public void actualizarHora(Label lblHora){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        lblHora.setText(LocalTime.now().format(formatter));
    }

}
