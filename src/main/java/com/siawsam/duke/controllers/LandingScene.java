package com.siawsam.duke.controllers;

import java.io.IOException;

import com.siawsam.duke.Duke;
import com.siawsam.duke.Response;
import com.siawsam.duke.Ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class LandingScene extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    
    private final Stage stage;
    private final Duke duke;
    
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(getClass().getResourceAsStream("/images/duke.png"));
    
    /**
     * Constructs a new scene that represents the main landing view of Duke.
     *
     * @param duke The Duke instance to attach the GUI to.
     * @param stage The stage to display this scene on.
     */
    public LandingScene(Duke duke, Stage stage) {
        this.stage = stage;
        this.duke = duke;
    }
    
    /**
     * Initializes a Landing Scene by loading saved Duke data.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    
        String initializationMessage;
        try {
            Response loadOperationResponse = duke.loadFromDisk();
            initializationMessage = loadOperationResponse.getMessage();
        } catch (IOException ex) {
            initializationMessage = "An error occurred while trying to read the save file.";
        } catch (ClassNotFoundException ex) {
            initializationMessage = "The save file does not contain a saved Duke task list.";
        }
    
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.showWelcomeMessage(), dukeImage),
                DialogBox.getDukeDialog(initializationMessage, dukeImage)
        );
    }
    
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.readAndExecute(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getMessage(), dukeImage)
        );
        userInput.clear();
        
        System.out.println(response.getMessage());
        // terminate application if a terminating response is received
        if (response.isTerminating()) {
            stage.close();
        }
    }
}
