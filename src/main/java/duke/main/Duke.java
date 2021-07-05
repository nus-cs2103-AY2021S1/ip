package duke.main;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.DialogBox;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.Stage;

/**
 * Encapsulates the core class which coordinates with all other key classes to
 * take in input, process it and produce the output that the Duke program displays
 * to its users.
 */
public class Duke extends Application {

    // attributes for multi-class coordination
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser = new Parser();

    // attributes for JavaFX
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/NewDuke.png"));

    /**
     * Creates a Duke object which holds a file path and uses it to load
     * the text file from the hard drive or create one if it does not yet exist.
     * It also creates new Ui and Storage objects for functionality.
     */
    public Duke() {
        ui = new Ui();
        String filePath = "data/duke.txt";
        storage = new Storage(filePath);
        try {
            storage.createFile();
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            handleExceptions("FileNotFound");
        } catch (DukeException e) {
            handleExceptions("DukeException");
        }
    }

    @Override
    public void start(Stage stage) {

        //Solution below is adapted from https://se-education.org/guides/tutorials/javaFx.html
        //Minor modifications were made

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

        //Step 2.5. Deal with exception handling and start message printing when app starts to run
        handleStart();
        showList();

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label(input), new ImageView(user)),
                DialogBox.getDukeDialog(new Label(response), new ImageView(duke))
        );
        userInput.clear();

        // exit Duke if "bye" was called
        if (input.equals("bye") || input.equals("b")) {
            Platform.exit();
        }
    }

    private void handleExceptions(String errorName) {
        assert (errorName != null) : "The error name cannot be null!";
        String printedStatement = "";
        if (errorName.equals("FileNotFound")) {
            printedStatement = ui.loadFileError();
        }
        if (errorName.equals("DukeException")) {
            printedStatement = ui.loadDateError();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(printedStatement), new ImageView(duke)));
    }

    private void handleStart() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.showStartMessage()), new ImageView(duke)));
    }

    private void showList() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.showCurrentTasks(tasks.getTaskList())),
                        new ImageView(duke)));
    }

    private String getResponse(String input) {
        assert (input != null) : "The input cannot be null!";
        ui.showCurrentTasks(tasks.getTaskList());
        try {
            Command c = parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException ex) {
            return ui.printExceptions(ex);
        }
    }

}
