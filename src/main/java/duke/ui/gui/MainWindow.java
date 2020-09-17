package duke.ui.gui;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import duke.DukeCommandMatcher;
import duke.parsers.MarkdownParser;
import duke.storage.Storage;
import duke.ui.Printer;
import duke.ui.gui.markdown.ErrorText;
import duke.ui.gui.markdown.Markdown;
import duke.ui.gui.markdown.NormText;
import duke.utils.Constants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


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
    @FXML
    private HBox userPanel;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DonaldTrump.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    private final ChangeListener<Boolean> inputFocusListener = this::handleFocusedInputField;

    /**
     * Initializer for main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        NormText greetingText = new NormText(Constants.GREETING);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Arrays.asList(greetingText), dukeImage));
        userInput.focusedProperty().addListener(this.inputFocusListener);
    }

    @FXML
    private void handleFocusedInputField(ObservableValue<? extends Boolean> observableValue,
                                         Boolean oldVal, Boolean newVal) {
        if (newVal) {
            userInput.setStyle("-fx-background-radius: 10");
            userPanel.setPadding(new Insets(3, 5, 3, 5));
            userPanel.setSpacing(10);
            userInput.setPrefWidth(325);
        } else {
            userInput.setStyle("-fx-background-radius: 30");
            userPanel.setPadding(new Insets(3, 5, 3, 25));
            userInput.setPrefWidth(275);
            userPanel.setSpacing(30.0);
        }
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        //handle input
        if (!Objects.equals(input.trim(), "")) {
            NormText inputText = new NormText(input);
            dialogContainer.getChildren().add(DialogBox.getUserDialog(Arrays.asList(inputText), userImage));
            handleDukeOutput(input);
            userInput.clear();
        }
    }

    private void handleDukeOutput(String input) {
        try {
            Storage database = new Storage("data/tasksTable.csv");
            DukeCommandMatcher parser = new DukeCommandMatcher(database);
            String response = parser.handleCommand(input);
            MarkdownParser mdParser = new MarkdownParser(response);
            List<Markdown> markdownList = mdParser.parse();
            DialogBox db;
            if (Objects.equals(response, Constants.EXITRESPONSE)) {
                NormText byeText = new NormText(Printer.printBye());
                db = DialogBox.getDukeDialog(Arrays.asList(byeText), dukeImage);
                userInput.setEditable(false);
                userInput.setStyle("-fx-background-color: grey;");
                userInput.focusedProperty().removeListener(this.inputFocusListener);
            } else {
                assert response != Constants.EXITRESPONSE : response;
                db = DialogBox.getDukeDialog(markdownList, dukeImage);
            }
            dialogContainer.getChildren().add(db);
        } catch (Exception e) {
            ErrorText exceptionText = new ErrorText(e.getMessage());
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(Arrays.asList(exceptionText), dukeImage));
        }
    }

}
