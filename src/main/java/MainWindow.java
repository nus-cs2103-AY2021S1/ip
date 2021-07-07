import duke.command.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Image markLeeImage = new Image(this.getClass().getResourceAsStream("/images/marklee.jpg"));
    private Image jackie_default_Image = new Image(this.getClass().getResourceAsStream("/images/jackie_default.jpg"));
    private Image jackie_what_Image = new Image(this.getClass().getResourceAsStream("/images/jackie_what.jpg"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        if (Command.isTerminated) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
            return;
        }

        String input = userInput.getText();
        duke.run(input);
        String error = duke.getErrorString();
        if (error.isBlank()) {
            String response = duke.getUiUpdate();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, markLeeImage),
                    DialogBox.getDukeDialog(response, jackie_default_Image)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, markLeeImage),
                    DialogBox.getErrorDialog(error, jackie_what_Image)
            );
        }


        userInput.clear();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty()); //Set auto scroll
    }

    public void update() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getUiUpdate(), jackie_default_Image)
        );
    }

    public void handleCloseBtnClicked() {
        duke.run("exit");
    }
}

