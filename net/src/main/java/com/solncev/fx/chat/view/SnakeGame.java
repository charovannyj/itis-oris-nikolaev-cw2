package com.solncev.fx.chat.view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends Application {
    double snakeRadius = 10;
    double appleRadius = 5;
    Circle apple;
    double centerX = 100;
    double centerY = 100;
    double newTimeSleep = 0;
    int timeSleep = 100_000_000;
    double sceneX = 400;
    double sceneY = 400;
    long elapsedTime = 0;
    boolean appleIsExist = false;
    Pane pane = new Pane();
    KeyCode direction = KeyCode.RIGHT;
    List<Integer> applesCoordinatesX = new ArrayList<>();
    List<Integer> applesCoordinatesY = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        Label timerLabel = new Label("00:00");
        timerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        timerLabel.setTextFill(Color.RED);
        timerLabel.setLayoutX(20);
        timerLabel.setLayoutY(20);

        Scene gameScene = new Scene(pane, sceneX, sceneY);
        gameScene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.RIGHT)||
                keyEvent.getCode().equals(KeyCode.LEFT)||
                keyEvent.getCode().equals(KeyCode.UP)||
                keyEvent.getCode().equals(KeyCode.DOWN)){
                direction = keyEvent.getCode();
            }
        });
        stage.setScene(gameScene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            long lastUpdate = 0;


            @Override
            public void handle(long now) {
                double k = 0.8;
                if (now - lastUpdate >= timeSleep) {
                    if (elapsedTime%60 == 0){
                        timeSleep*=k;

                    }
                    generateAppleIfNotExist();
                    isEat();
                    lastUpdate = now;

                    elapsedTime += 1/k;
                    long minutes = (elapsedTime * timeSleep / 1_000_000_000 / 60) % 60;
                    long seconds = (elapsedTime * timeSleep / 1_000_000_000) % 60;
                    String timeString = String.format("%02d:%02d", minutes, seconds);
                    timerLabel.setText(timeString);
                    if (gameOver()){
                        timerLabel.setText("Game over");
                        direction = KeyCode.SPACE;
                    }
                    if (direction == KeyCode.RIGHT) {
                        centerX += 5;
                    } else if (direction == KeyCode.LEFT) {
                        centerX -= 5;
                    } else if (direction == KeyCode.UP) {
                        centerY -= 5;
                    } else if (direction == KeyCode.DOWN) {
                        centerY += 5;
                    } else{

                    }

                    Circle circle = new Circle(snakeRadius);
                    circle.setCenterX(centerX);
                    circle.setCenterY(centerY);
                    pane.getChildren().clear();
                    pane.getChildren().add(circle);
                    pane.getChildren().add(apple);
                    pane.getChildren().add(timerLabel);

                }
            }
        };
        timer.start();
    }

    public void generateAppleIfNotExist() {
        if (!appleIsExist) {
            applesCoordinatesX.add(new Random().nextInt((int) sceneX));
            applesCoordinatesY.add(new Random().nextInt((int) sceneY));
            apple = new Circle(5);
            apple.setCenterX(applesCoordinatesX.get(0));
            apple.setCenterY(applesCoordinatesY.get(0));
            apple.setFill(Color.RED);
            appleIsExist = true;
        }
    }

    public boolean isEat() {
        if (Math.pow(appleRadius + snakeRadius, 2) > Math.pow(Math.abs(centerX - applesCoordinatesX.get(0)), 2) + Math.pow(Math.abs(centerY - applesCoordinatesY.get(0)), 2)) {
            applesCoordinatesX.clear();
            applesCoordinatesY.clear();
            appleIsExist = false;
            snakeRadius += 5;
            return true;
        }
        return false;
    }

    public boolean gameOver() {
        return centerX + snakeRadius > sceneX || centerY + snakeRadius > sceneY || centerY < snakeRadius || centerX < snakeRadius;
    }
}
