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

    /**
     * Initializes the main window of the GUI with a welcome message from the bot.
     */
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

    /**
     * Sets the bot's logic with the given bot.
     * @param d the bot.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Upon selection of a date using the DatePicker in the GUI, this
     * method appends the selected date's text to the user input text field.
     * @param event the event of DatePicker selecting a date.
     */
    public void appendDateTextToUserInput(ActionEvent event) {
        try {
            LocalDate date = datePicker.getValue();
            String currentText = userInput.getText();
            userInput.setText(currentText.strip() + " "
                    + date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            datePicker.getEditor().clear();
            datePicker.setValue(null);
        } catch (NullPointerException e) {
            System.out.println("User has selected a date from DatePicker.");
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
