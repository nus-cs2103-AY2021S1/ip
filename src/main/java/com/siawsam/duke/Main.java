package com.siawsam.duke;

import com.siawsam.duke.controllers.LandingScene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the entry point for the {@link Duke Duke} GUI application.
 */
public class Main extends Application {
    private final Duke duke = new Duke();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Duke Chat Bot");
        
        // load and set up scene
        FXMLLoader loader = new FXMLLoader(Duke.class.getResource("/view/LandingScene.fxml"));
        loader.setController(new LandingScene(duke, primaryStage));
        AnchorPane ap = loader.load();
        Scene scene = new Scene(ap);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
