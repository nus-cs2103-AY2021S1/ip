package duke;

import duke.text.TextCacher;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WindowDisplay extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private CommandParserAndLogic commandParserAndLogic;

    private Stage stagePointer;

    //@@author Jeffry Lum-reused
    //Code below reused from sub pages under https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Hendey's IP Task Manager Bot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // My setup for this to work

        this.mySetup(stage);

        // my stuff ends

        // Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());
    }
    //@@author Jeffry Lum

    // below are private methods only used in this class

    private void mySetup(Stage stage) {
        TextCacher.cacheStartMessage();
        flushTextCache();

        stagePointer = stage;

        // Location of save here
        Path pathToSave = Paths.get(System.getProperty("user.home"), "ipSave.txt");
        TaskList taskList = FileManager.readFromSave(pathToSave);

        flushTextCache();

        commandParserAndLogic = new CommandParserAndLogic(taskList, pathToSave);
        // set up done
        TextCacher.cachePromptMsg();
        flushTextCache();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        if (!commandParserAndLogic.hasEnded) { // stops users from interacting if it has already ended

            // echos input to the text row
            Label userText = new Label(userInput.getText());

            // sends input into the command chain to do its logic and get a return text
            Label dukeText = new Label(getResponse(userInput.getText()));

            // sends them out onto the display
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
            );
            userInput.clear();

            // checks if user has ended the program with previous command
            if (commandParserAndLogic.hasEnded) {
                endLogic();
            }
        } else {
            TextCacher.cacheStopDisturb();
            flushTextCache();
        }
    }

    /**
     * Sends input into the parsing and command logic
     *
     * @return The text cached in the TextCacher cached by the logic
     */
    private String getResponse(String input) {
        commandParserAndLogic.handleInput(input);
        return TextCacher.getStore();
    }

    private void endLogic() {
        TextCacher.cacheEndMessage();
        flushTextCache();

        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> stagePointer.close());
        delay.play();
    }

    private void flushTextCache() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Label(TextCacher.getStore()), new ImageView(duke)));
    }
}

