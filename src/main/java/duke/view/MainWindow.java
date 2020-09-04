package duke.view;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import duke.response.Response;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Duke duke;
    private final Image userImage;
    private final Image dukeImage;

    /**
     * The main window for Duke,
     *
     * @param duke A Duke instance.
     */
    public MainWindow(Duke duke) {
        this.duke = duke;
        this.userImage = new Image(this.getClass().getResourceAsStream("/images/user-solid.png"));
        this.dukeImage = new Image(this.getClass().getResourceAsStream("/images/crown-solid.png"));

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/views/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(this.duke.getWelcome(), this.dukeImage));
    }

    @FXML
    private void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        Response response = this.duke.getResponse(this.userInput.getText());

        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getDukeDialog(response.getMessage(), this.dukeImage)
        );
        this.userInput.clear();

        if (response.isExit()) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };

            this.userInput.setDisable(true);
            this.sendButton.setDisable(true);
            timer.schedule(task, 2000);
        }
    }
}
