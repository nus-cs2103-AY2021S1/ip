package com.siawsam.duke;

import com.siawsam.duke.controllers.LandingScene;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
    private final Duke duke = new Duke();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Duke Chatbot");
        
        String resource;
        try {
            duke.loadFromDisk();
            resource = "/view/LandingScene.fxml";
        } catch (IOException ex) {
            System.out.println("IO exception");
            resource = "/view/IOExceptionScene.fxml";
        } catch (ClassNotFoundException ex) {
            System.out.println("invalid save file");
            resource = "/view/InvalidSaveFile.fxml";
        }
        
        FXMLLoader loader = new FXMLLoader(Duke.class.getResource(resource));
        loader.setController(new LandingScene(duke, primaryStage));
        AnchorPane ap = loader.load();
        Scene scene = new Scene(ap);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
