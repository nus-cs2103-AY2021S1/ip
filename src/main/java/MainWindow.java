import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.concurrent.CompletableFuture;


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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        DialogBox userDialog = DialogBox.getUserDialog(userInput.getText(), userImage);
        userDialog.setBorder(new Border(new BorderStroke(Color.ORANGERED,
                                                         BorderStrokeStyle.SOLID,
                                                         new CornerRadii(20.0),
                                                         BorderWidths.DEFAULT)));
        DialogBox dukeDialog = DialogBox.getDukeDialog(duke.getResponse(userInput.getText()), dukeImage);
        dukeDialog.setBorder(new Border(new BorderStroke(Color.ALICEBLUE,
                                                         BorderStrokeStyle.SOLID,
                                                         new CornerRadii(20.0),
                                                         BorderWidths.DEFAULT)));
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        userInput.clear();

        //Solution below adapted from https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java#:~:text=7%20Answers&text=Create%20a%20class%20that%20implements,call%20start()%20on%20it
        if(!duke.taskList.isUpdating){
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        duke.save();
                        System.exit(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
        }
    }
}