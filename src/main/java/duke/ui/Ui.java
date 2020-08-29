package duke.ui;

import duke.Storage;
import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tool.Parser;
import duke.tool.TaskList;
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

import java.util.ArrayList;

/**
 * Represents the UI manager for the system.
 */
public class Ui {

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/me.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/ben.png"));

    /**
     * Logo of Duke.
     */
    private String logo =  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Storage of the system
     */
    private final Storage storage;

    /**
     * Task list that stores tasks
     */

    private final TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private Stage stage;



    /**
     * Welcome message
     */
    private static final String WELCOME_MESSAGE = "Hello, I am Duke! \n\t What can I do for you?";



    /**
     * Horizontal Line
     */
    private static final String LINE = "-------------------------------------";

    public Ui(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    public void process(Stage stage) {
        this.stage = stage;
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();


        //Formatting the window
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Label welcome = new Label(Ui.WELCOME_MESSAGE);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcome,new ImageView(duke)));

        sendButton.setOnMouseClicked((event -> handleUserInput()));

        userInput.setOnAction((event -> handleUserInput()));

        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {

        String fullCommand = userInput.getText();

        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, storage);

            if (c.isExit()) {
                userInput.clear();
                stage.close();
            }

            Label userText = new Label(fullCommand);
            Label dukeText = new Label(c.getResponse());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
            );
            userInput.clear();
        } catch (DukeException e) {
            printLog(e.getMessage());
            Label errorMsg = new Label(e.getMessage());
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(errorMsg,new ImageView(duke)));
        }
    }

    /**
     * Prints the log of Duke.
     *
     * @param s Log content.
     */
    public void printLog(String s) {
        System.out.println(formatOut(s));
    }

    /**
     * Print the task list in the system.
     *
     * @param tasks Task list in the system.
     */
    public static String getTaskListString(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list: \n\t");

        //Check whether there are any task in the list or not
        if (taskList.isEmpty()) {
            return "You haven't added any task here !";
        }

        //Produce output string
        for (Task task : taskList) {
            builder.append(taskList.indexOf(task) + 1).append(". ")
                    .append(task.toString()).append("\n").append("\t");
        }

        return builder.toString();
    }


    /**
     * Returns a formatted string that Duke interacts with users.
     *
     * @param s An input String.
     * @return A formatted string with built in format.
     */
    private String formatOut(String s) {
        return String.format("  %s\n    %s\n  %s\n", Ui.LINE, s, Ui.LINE);
    }
}
