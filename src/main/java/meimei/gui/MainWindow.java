package meimei.gui;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import meimei.MeimeiBot;

/**
 * Controller for meimei.gui.MainWindow. Provides the layout for the other controls.
 * Adapted from <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">this guide</a>.
 */
public class MainWindow extends AnchorPane {
    public static final int EXIT_DELAY_TIME_IN_MILLISECONDS = 5000;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final MeimeiBot meimeiBot;

    private final Image userProfile = new Image(this.getClass().getResourceAsStream("/images/RalphProfile.png"));
    private final Image meimeiProfile = new Image(this.getClass().getResourceAsStream("/images/VanellopeProfile.png"));

    /**
     * Public constructor for the main window of the bot's GUI.
     *
     * @param meimeiBot Meimei Bot instance that is used for the GUI instance.
     */
    public MainWindow(MeimeiBot meimeiBot) {
        this.meimeiBot = meimeiBot;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getMeimeiDialog(this.meimeiBot.start(), meimeiProfile));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing the bot's reply and then appends them to
     * the dialog container. Clears the user input after processing. Exits if bot stops running.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = meimeiBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userProfile),
                DialogBox.getMeimeiDialog(response, meimeiProfile)
        );
        userInput.clear();

        if (!meimeiBot.isRunning()) {
            // @@author foojingyi-reused
            // Reused from https://github.com/PrestonTYR/ip/blob/master/src/main/java/duke/gui/MainWindow.java
            // with minor modifications.
            Duration delayDuration = new Duration(EXIT_DELAY_TIME_IN_MILLISECONDS);
            KeyFrame keyFrame = new KeyFrame(delayDuration, x -> Platform.exit());
            Timeline tl = new Timeline(keyFrame);
            tl.play();
        }
    }
}
