package duke.ui;

import duke.command.Command;
import duke.exception.DukeArgumentException;
import duke.exception.DukeExecutionException;
import duke.exception.DukeIoException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.component.DialogBox;
import duke.ui.component.ScrollDialoguePane;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Gui extends Application {

    private final String PATH = "data.txt";
    private final Image IMG_USER = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image IMG_DUKE = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    private final DialogBox GREETING = DialogBox.dukeDialog(getDialogLabel("Hello."), new ImageView(IMG_DUKE));
    /* Sets a black 1px border with 2px padding around the Label.*/
    private final String CSS_LABEL =
            "-fx-padding: 5px;\n" +
            "-fx-text-fill: EEEBE7;\n";

    private TextField userInput = new TextField();
    private Button sendButton = new Button("Send");
    ScrollDialoguePane scrollDialoguePane = new ScrollDialoguePane();
    Storage storage = new Storage(PATH);

    // One second delay for sending the "bye" before closing.
    PauseTransition PAUSE = new PauseTransition(Duration.seconds(1));

    public Gui() throws DukeIoException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Duke");
        scrollDialoguePane.setPrefSize(385, 535);
        scrollDialoguePane.addDialog(GREETING);

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(userInput, sendButton, scrollDialoguePane);
        layout.setPrefSize(400.0, 600.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Scene scene = new Scene(layout);
        stage.setScene(scene);

        stage.show();

        sendButton.setOnMouseClicked(event -> {
            if (handleUserInput()) {
                PAUSE.setOnFinished(event1 -> {
                    stage.close();
                });
                PAUSE.playFromStart();
            }
            userInput.clear();
        });

        userInput.setOnAction(event -> {
            if (handleUserInput()) {
                PAUSE.setOnFinished(event1 -> {
                    stage.close();
                });
                PAUSE.playFromStart();
            }
            userInput.clear();
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setStyle(CSS_LABEL);
        textToAdd.setTextFill(Color.web("#FFFFFF")); // white text
        return textToAdd;
    }

    private boolean handleUserInput() {
        String input = userInput.getText();
        boolean shouldExit = false;
        try {
            Command command = Parser.parseCommand(input);
            DialogBox user = userDialog(input);
            scrollDialoguePane.addDialog(user);
            scrollDialoguePane.addDialog(dukeDialog(command.execute(storage)));
            shouldExit = command.shouldExit();
        } catch (DukeArgumentException e) {
            DialogBox user = userDialog(input);
            scrollDialoguePane.addDialog(user);
            scrollDialoguePane.addDialog(dukeDialog(e.getMessage()));
        } catch (DukeExecutionException e) {
            DialogBox user = userDialog(input);
            scrollDialoguePane.addDialog(user);
            scrollDialoguePane.addDialog(dukeDialog(e.getMessage()));
        }
        return shouldExit;
    }

    private DialogBox dukeDialog(String text) {
        return  DialogBox.dukeDialog(getDialogLabel(text), new ImageView(IMG_DUKE));
    }

    private DialogBox userDialog(String text) {
        return  DialogBox.userDialog(getDialogLabel(text), new ImageView(IMG_USER));
    }
}
