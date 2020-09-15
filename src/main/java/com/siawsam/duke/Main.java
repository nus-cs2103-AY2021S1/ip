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
    private static final String STAGE_TITLE = "Duke Chat Bot";
    private static final String DEFAULT_SCENE = "/view/LandingScene.fxml";
    private static final String STYLESHEET = "/styles/styles.css";
    private final Duke duke = new Duke();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(STAGE_TITLE);
        
        // load and set up scene
        FXMLLoader loader = new FXMLLoader(Duke.class.getResource(DEFAULT_SCENE));
        loader.setController(new LandingScene(duke, primaryStage));
        AnchorPane ap = loader.load();
        Scene scene = new Scene(ap);
        scene.getStylesheets().add(STYLESHEET);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
