package controller;

import java.io.IOException;

import duke.Duke;
import duke.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Ui ui;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Serina.jpg"));

    /**
     * Creates a new Main Window UI
     */
    public MainWindow() {
        ui = new Ui();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bind action handlers to userInput and sendButton
     */
    @FXML
    public void initialize() {
        userInput.setOnAction(event -> handleUserInput());
        sendButton.setOnAction(event -> handleUserInput());
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Sets duke in MainWindow and creates initial startup dialogContainer displaying welcome message.
     *
     * @param d Duke instance.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.getUi().showWelcome(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.equals(ui.showGoodbye())) {
            Platform.exit();
            System.exit(0);
        }
    }
}
