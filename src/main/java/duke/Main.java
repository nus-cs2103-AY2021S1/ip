package src.main.java.duke;

import duke.ui.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    
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
        stage.setTitle("Duke");
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

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

//
///**
// * Entry point of the Best2103Bot
// * Initializes the application and starts the interaction with user.
// */
//public class Main {
//
//    private TextUi ui;
//    private StorageFile storage;
//    private Duke duke;
//
//    public static void main(String... launchArgs) {
//        new Main().run(launchArgs);
//    }
//
//    /**
//     * Run the program until termination
//     */
//    public void run(String[] launchArgs) {
//        start (launchArgs);
//        runCommandLoopUntilExitCommand();
//        exit();
//    }
//
//    /**
//     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
//     *
//     * @param launchArgs arguments supplied by the user at program launch
//     *
//     */
//    private void start(String[] launchArgs) {
//        try {
//            this.ui = new TextUi();
//            this.storage = initializeStorage(launchArgs);
//            this.duke = storage.load();
//            // Show welcome message and file path
//            ui.showWelcomeMessage();
//
//        } catch (InvalidStorageFilePathException | StorageOperationException e) {
//            ui.showInitFailedMessage();
//            throw new RuntimeException(e);
//        }
//    }
//
//    /** Prints the Goodbye message and exits. */
//    private void exit() {
//        ui.showGoodbyeMessage();
//        System.exit(0);
//    }
//
//    /** Reads the user command and executes it, until the user issues the exit command.  */
//    private void runCommandLoopUntilExitCommand() {
//        Command command;
//        do {
//            String userCommandText = ui.getUserCommand();
//            command = new Parser().parseCommand(userCommandText);
//            CommandResult result = executeCommand(command);
//            ui.showResultToUser(result);
//
//        } while (!ExitCommand.isExit(command));
//    }
//
//    /**
//     * Executes the command and returns the result.
//     *
//     * @param command user command
//     * @return result of the command
//     */
//    private CommandResult executeCommand(Command command) {
//        try {
//            command.setData(duke);
//            CommandResult result = command.execute();
//            storage.save(duke);
//            return result;
//        } catch (Exception e) {
//            ui.showToUser(e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
//     * @param launchArgs arguments supplied by the user at program launch
//     * @throws InvalidStorageFilePathException if the target file path is incorrect.
//     */
//    private StorageFile initializeStorage(String[] launchArgs) throws InvalidStorageFilePathException {
//        boolean isStorageFileSpecifiedByUser = launchArgs.length > 0;
//        return isStorageFileSpecifiedByUser ? new StorageFile(launchArgs[0]) : new StorageFile();
//    }
//}
