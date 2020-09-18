package duke.gui;

import java.io.File;

import duke.commands.Command;
import duke.commands.InvalidCommand;
import duke.exceptions.InvalidDukeCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Represents Duke object. Is responsible for the overall running of Duke.
 *
 * @author Kishen Ashok Kumar
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage store;
    private TaskList tasks;
    private Ui ui;
    private Image userImage;
    private Image dukeImage;
    private Image iconImage;
    private boolean isExit;

    /**
     * Creates a Duke object and sets all the attributes
     * to null.
     */
    public Duke() {
        store = new Storage("./data", "duke.txt");
        this.ui = new Ui();
        File loadFile = store.loadData(ui);
        if (loadFile == null) {
            tasks = new TaskList();
        } else {
            tasks = new TaskList(loadFile);
        }
        userImage = new Image(
                this.getClass().getResourceAsStream("/images/DaUser.png"));
        dukeImage = new Image(
                this.getClass().getResourceAsStream("/images/DaDuke.png"));
        iconImage = new Image(
                this.getClass().getResourceAsStream("/images/DaIcon.png"));
    }

    /**
     * Creates a Duke object with the respective directory and file paths
     * for loading and saving of task list.
     *
     * @param directoryPath directory path containing text file to load.
     * @param filePath name of text file to load.
     */
    public Duke(String directoryPath, String filePath) {
        ui = new Ui();
        store = new Storage(directoryPath, filePath);
        File loadFile = store.loadData(ui);
        if (loadFile != null) {
            tasks = new TaskList(loadFile);
        } else {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the instance of Duke.
     * Is responsible for the coordination of all Duke functions.
     */
    public void run() {
        ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, store);
                isExit = command.isExit();
                if (!isExit) {
                    ui.displayBlankLine();
                }
            } catch (InvalidDukeCommand e) {
                new InvalidCommand().execute(tasks, ui, store);
            }

        }
    }

    /**
     * Creates an instance of a Duke object and runs it.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data", "duke.txt");
        duke.run();
    }

    /**
     * Starts JavaFX.
     * @param stage Stage object to show the user.
     */
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setStyle("-fx-background-image: url(\"/images/DaBackground.png\");");;
        scrollPane.setContent(dialogContainer);
        dialogContainer.setSpacing(20);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // more code to be added here later
        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke Task Tracker");
        stage.getIcons().add(iconImage);
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

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        ImageView dukePic = new ImageView(dukeImage);
        dukePic.setClip(new Circle(50, 50, 50));

        DialogBox dukeGreeting = DialogBox.getDukeDialog(new Label(ui.displayGreeting()), dukePic);
        dukeGreeting.setStyle("-fx-background-radius: 20px; -fx-background-color: #35C9DD;");

        Insets padding = new Insets(10, 0, 10, 0);
        dukeGreeting.setPadding(padding);
        dukeGreeting.setSpacing(10);

        dialogContainer.getChildren().addAll(dukeGreeting);

        // more code to be added here later
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        String invalidInput = "Looks like your input was invalid! Enter --help for more information";
        String invalidCommand = "Invalid command/format! check --help for more info";
        String invalidDeadline = "Invalid deadline task!";
        String invalidEvent = "Invalid event task!";
        String invalidTodo = "Invalid todo task!";
        String noSuchElement = "There's no such element!";

        Label userText = new Label(userInput.getText());

        String response = getResponse((userInput.getText()));
        boolean isInvalidCommandOrElement = response.equals(invalidCommand) || response.equals(noSuchElement)
                                                    || response.equals(invalidInput);
        boolean isInvalidTaskFormat = response.equals(invalidDeadline) || response.equals(invalidEvent)
                                              || response.equals(invalidTodo);
        Label dukeText = new Label(response);
        if (isInvalidCommandOrElement || isInvalidTaskFormat) {
            dukeText.setStyle("-fx-text-fill: rgb(100%, 0%, 0%);");
        }

        ImageView dukePicture = new ImageView(dukeImage);
        ImageView userPicture = new ImageView(userImage);
        Insets padding = new Insets(10, 0, 10, 0);
        dukePicture.setClip(new Circle(50, 50, 50));
        userPicture.setClip(new Circle(50, 50, 50));

        DialogBox userDialog = DialogBox.getUserDialog(userText, userPicture);
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText, dukePicture);
        userDialog.setPadding(padding);
        dukeDialog.setPadding(padding);
        userDialog.setSpacing(10);
        dukeDialog.setSpacing(10);


        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        userInput.clear();

        if (isExit) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();

        }
    }

    private String getResponse(String input) {
        try {
            Command command = Parser.parse(input);;
            String response = command.execute(tasks, ui, store);
            isExit = command.isExit();
            return response;
        } catch (InvalidDukeCommand e) {
            String response = new InvalidCommand().execute(tasks, ui, store);
            return response;
        }
    }

}
