package viscount.gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import viscount.Main;
import viscount.Viscount;

/**
 * Represents the main window component of the GUI.
 *
 * Handles user input and display of Viscount's responses on the GUI.
 */
public class MainWindow extends AnchorPane {
    private static final String DATA_DIRECTORY_PATH = System.getProperty("user.dir") + "/data/";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Viscount viscount;

    /**
     * Instantiates a new MainWindow component.
     */
    public MainWindow() {
        try {
            this.viscount = new Viscount(DATA_DIRECTORY_PATH);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String viscountWelcome = viscount.getUi().getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getViscountDialog(viscountWelcome));
    }

    /**
     * Initialises the component.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Handles user input and displays Viscount's responses accordingly.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.equals("bye")) {
            Platform.exit();
        } else {
            String response = viscount.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input),
                    DialogBox.getViscountDialog(response)
            );
        }

        userInput.clear();
    }
}
