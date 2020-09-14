package duke.gui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DukeGuiWindow extends BorderPane {

    @FXML
    public TextField userInput;
    @FXML
    private Label dukeMessage;
    @FXML
    private Label userMessage;

    private Duke duke;
    private Stage stage;

    public void setUp(Duke duke, Stage stage) {
        this.duke = duke;
        this.stage = stage;
    }

    /**
     * Sets up things on start.
     */
    public void onStart() {
        duke.getGuiResponse().greet();
        dukeMessage.setText(duke.getGuiResponse().getResponse());
    }

    /**
     * Handles the user input.
     * @throws IOException
     */
    public void handleUserInput() throws IOException {
        String input = userInput.getText();
        userMessage.setText(input);
        userInput.clear();

        String response = duke.getResponse(input);
        dukeMessage.setText(response);

        if (duke.getState().getExitLoop()) {
            duke.onExit();
            stage.close();
        }
    }
}
