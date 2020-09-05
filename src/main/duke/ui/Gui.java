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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Gui extends Application {

    private final String filePath = "data.txt";
    private final Image imgUser = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image imgDuke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    /* Sets a black 1px border with 2px padding around the Label.*/
    private final String cssLabel =
            "-fx-padding: 5px;\n"
                    + "-fx-text-fill: EEEBE7;\n";
    private ScrollDialoguePane scrollDialoguePane = new ScrollDialoguePane();
    private Storage storage = new Storage(filePath);
    private final TextField userInput = new TextField();
    private final Button sendButton = new Button("Send");
    // One second delay for sending the "bye" before closing.
    private final PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));

    public Gui() throws DukeIoException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Duke");
        scrollDialoguePane.setPrefSize(385, 535);
        DialogBox greeting = DialogBox.dukeDialog(getDialogLabel("Hello."), new ImageView(imgDuke));
        scrollDialoguePane.addDialog(greeting);

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
                pauseTransition.setOnFinished(event1 -> {
                    stage.close();
                });
                pauseTransition.playFromStart();
            }
            userInput.clear();
        });

        userInput.setOnAction(event -> {
            if (handleUserInput()) {
                pauseTransition.setOnFinished(event1 -> {
                    stage.close();
                });
                pauseTransition.playFromStart();
            }
            userInput.clear();
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setStyle(cssLabel);
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
        return DialogBox.dukeDialog(getDialogLabel(text), new ImageView(imgDuke));
    }

    private DialogBox userDialog(String text) {
        return DialogBox.userDialog(getDialogLabel(text), new ImageView(imgUser));
    }
}
