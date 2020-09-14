package duke.gui;

import duke.Duke;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller for MainWindow. Provides layout for the other controls.
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
    @FXML
    private DatePicker datePicker;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/tebby.png"));
    private final Image dukeHuhImage = new Image(this.getClass().getResourceAsStream("/images/tebbyHuh.png"));
    private final Image dukeErrorImage = new Image(this.getClass().getResourceAsStream("/images/tebbyError.png"));

    @FXML
    public void initialize() {
        System.out.println("=================== [ Tebby started in GUI mode ] ===================");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        datePicker.setTooltip(new Tooltip("Pick a date"));
        duke = new Duke();
        String welcomeMessage = duke.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                new DukeDialogBox(welcomeMessage, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void getDateText(ActionEvent event) {
        try {
            LocalDate date = datePicker.getValue();
            String currentText = userInput.getText();
            userInput.setText(currentText.strip() + " "
                    + date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            datePicker.getEditor().clear();
            datePicker.setValue(null);
        } catch (NullPointerException e) {
            System.out.println("DatePicker has been reset.");
        }
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        if (!input.isEmpty()) {
            System.out.println("User: " + input);
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    new UserDialogBox(input, userImage),

                    response.startsWith("><")
                            ? new DukeErrorDialogBox(response, dukeErrorImage)
                            : response.startsWith("Sorry")
                                ? new DukeDialogBox(response, dukeHuhImage)
                                : new DukeDialogBox(response, dukeImage)
            );
        }
        userInput.clear();
    }
}
