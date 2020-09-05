package duke.ui.visualui;

import duke.Duke;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

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
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane userScreen; 
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private VBox reminderSection;
    
    private Duke duke;
    Stage stage;
    private Image stitch = new Image(this.getClass().getResourceAsStream("/images/stitch.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image welcomeStitch = new Image(this.getClass().getResourceAsStream("/images/welcomestitch.png"));

    @FXML
    public void initialize() {
        userScreen.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke, Stage stage) {
        this.duke = duke;
        Ui ui = new Ui();
        String greet = ui.greetings();
        this.stage = stage;
        dialogContainer.getChildren().addAll(OpeningBox.getOpeningMessage(greet, welcomeStitch));
        userScreen.prefWidthProperty().bind(stage.widthProperty());
        
        TaskList taskList = duke.retrieveTaskList();
        TaskList reminderList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getReminderStatus() == 1) {
                reminderList.add(taskList.get(i));
            }
        }
        reminderList.sortByDueDate();
        for (int i = 0; i < reminderList.size(); i++) {
            reminderSection.getChildren().addAll(ReminderDisplay.getReminderDisplay(reminderList.get(i)));
        }
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
                DialogBox.getUserDialog(stage, input, user),
                DialogBox.getDukeDialog(stage, response, stitch)
        );
        userInput.clear();
    }
}
