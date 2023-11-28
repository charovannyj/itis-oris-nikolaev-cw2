package com.solncev.fx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene main = new Scene(root,600,600);
        stage.setTitle("this is my 1st javax app");
        stage.setScene(main);
        stage.show();
    }
}
