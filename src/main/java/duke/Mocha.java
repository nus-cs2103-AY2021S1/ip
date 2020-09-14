package duke;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Mocha contains of the logic of the application.
 */
public class Mocha extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image mocha = new Image(this.getClass().getResourceAsStream("/images/Mocha.png"));

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor of Mocha object.
     */
    public Mocha() {
        ui = new Ui();
        parser = ui.createParser();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.loadData());
    }

    public static void main(String[] args) {
        //..
    }

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
        stage.setTitle("Mocha");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.getChildren().add(
                DialogBox.getMochaDialog(ui.sayIntroduction(), mocha));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mocha's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label mochaText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getMochaDialog(getResponse(userInput.getText()), mocha)
        );
        userInput.clear();
    }

    /**
     * Generates the response of Mocha according to the input of the user.
     *
     * @param input Takes in the input of the user.
     * @return the response of Mocha.
     */
    String getResponse(String input) {
        String responseReturn = "";

        try {
            int commandNumber = parser.parseCommand(input);
            assert commandNumber == -1 || commandNumber > 1 && commandNumber <= 8;

            if (commandNumber >= 1 && commandNumber <= 3) {
                Task newTask = parser.createTask(commandNumber);
                tasks.addTask(newTask);
                responseReturn = ui.addTask(newTask, tasks.getSize());

            } else if (commandNumber == 4) {
                int taskNumber = parser.getDoneTaskNumber();
                Task doneTask = tasks.getTask(taskNumber);
                doneTask.markAsDone();
                responseReturn = ui.markTaskDone(doneTask);

            } else if (commandNumber == 5) {
                responseReturn = ui.listAllTasks(tasks);

            } else if (commandNumber == 6) {
                responseReturn = ui.sayGoodbye();
                storage.writeToFile(tasks.getTaskList());

                PauseTransition delay = new PauseTransition(Duration.millis(1000));
                delay.setOnFinished(event-> Platform.exit());
                delay.play();
 
            } else if (commandNumber == 7) {
                int taskNumber = parser.getDeleteTaskNumber();
                Task deleteTask = tasks.getTask(taskNumber);
                tasks.deleteTask(taskNumber);
                responseReturn = ui.deleteTask(deleteTask, tasks.getSize());

            } else if (commandNumber == 8) {
                ArrayList<Task> matchingTasks = parser.getMatchingTasks(tasks);
                responseReturn = ui.findTask(matchingTasks);
                
            } else if (commandNumber == 9) {
                responseReturn = ui.help();
                
            } else {
                throw new CommandNotRecognizedException("Oops! I couldn't understand what you mean :(");
            }

        } catch (MissingTaskDescriptionException e) {
            responseReturn = e.getMessage();
        } catch (MissingTaskNumberException e) {
            responseReturn = e.getMessage();
        } catch (CommandNotRecognizedException e) {
            responseReturn = e.getMessage();
        }
        return responseReturn;
    }
}
